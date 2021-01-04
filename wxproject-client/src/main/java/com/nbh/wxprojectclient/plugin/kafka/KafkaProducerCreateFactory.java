package com.nbh.wxprojectclient.plugin.kafka;

import com.nbh.wxprojectcore.plugin.kafka.KafkaConstant;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static com.nbh.wxprojectclient.plugin.kafka.KafkaConfig.BOOTSTRAP_SERVERS_CONFIG;
import static com.nbh.wxprojectclient.plugin.kafka.KafkaConfig.CLIENT_ID_CONFIG;

@Configuration
public class KafkaProducerCreateFactory {


    private final static String STRING_TEMPLATE = "stringTemplate";
    private final static String BYTES_TEMPLATE = "bytesTemplate";

    /**
     * 字符串数组生产者
     *
     * @return
     */
    @Bean(name = STRING_TEMPLATE)
    public KafkaTemplate<String, String> stringTemplate() {
        KafkaTemplate stringTemplate = getTemplate(String.class, String.class, null);
        // 开启事务,注意从DefaultKafkaProducerFactory中过来的事务开启属性在构造template时会被移除掉，所以自定义template需要实现自己的事务前缀
        stringTemplate.setTransactionIdPrefix(STRING_TEMPLATE + CLIENT_ID_CONFIG);
        return stringTemplate;
    }

    /**
     * 字节数组生产者
     *
     * @return
     */
    @Bean(name = BYTES_TEMPLATE)
    public KafkaTemplate<String, byte[]> bytesTemplate() {
        return getTemplate(String.class, byte[].class, null);
    }


    private <K, V> KafkaTemplate<K, V> getTemplate(Class<K> kSerializer, Class<V> vSerializer, Map<String, Object> configMap) {
        if (CollectionUtils.isEmpty(configMap)) {
            configMap = new HashMap<>();
        }
        // 根据键值对设置序列化类
        if (null != kSerializer) {
            KafkaConstant.SerializerType kSerializerType = KafkaConstant.SerializerType.getSerializerByClass(kSerializer);
            if (!Objects.isNull(kSerializerType)) {
                configMap.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, kSerializerType.getSerializer());
            }
        }
        if (null != vSerializer) {
            KafkaConstant.SerializerType vSerializerType = KafkaConstant.SerializerType.getSerializerByClass(vSerializer);
            if (!Objects.isNull(vSerializerType)) {
                configMap.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, vSerializerType.getSerializer());
            }
        }
        return new KafkaTemplate<>(producerFactory(), configMap);
    }

    /**
     * 覆盖spring boot默认的
     *
     * @return
     */
    @Bean
    public ProducerFactory producerFactory() {
        DefaultKafkaProducerFactory defaultKafkaProducerFactory = new DefaultKafkaProducerFactory<>(producerConfigs());
        // 为每个线程创建一个单独的生产者
        // 如果producerPerThread是true，用户代码必须调用closeThreadBoundProducer()时，不再需要制作的工厂。这将物理上关闭生产者并将其从中移除ThreadLocal。reset()或destroy()将不会清理这些生产者。
//        defaultKafkaProducerFactory.setProducerPerThread(true);
        return defaultKafkaProducerFactory;
    }

    private Map<String, Object> producerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.CLIENT_ID_CONFIG, CLIENT_ID_CONFIG);
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS_CONFIG);
        props.put(ProducerConfig.ACKS_CONFIG, KafkaConstant.AcksType.ALL.getCode());
        // 默认32兆
        props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 33554432L);
        // 批量发送数据时最大阻塞时间1分钟
        props.put(ProducerConfig.MAX_BLOCK_MS_CONFIG, 1 * 60 * 1000);
        // 默认16k
        props.put(ProducerConfig.BATCH_SIZE_CONFIG, 16 * 1024);
        props.put(ProducerConfig.MAX_REQUEST_SIZE_CONFIG, 1 * 60 * 1000);
        props.put(ProducerConfig.COMPRESSION_TYPE_CONFIG, KafkaConstant.CompressionType.GZIP.getCode());
        // 最大重试次数为3次，如果三次失败后就以定时任务的方式进行添加
        props.put(ProducerConfig.RETRIES_CONFIG, 3);
        // 启用幂等性 当设置为true时， Producer 将确保每个消息在 Stream 中只写入一个副本。如果为false，
        // 由于 Broker 故障导致 Producer 进行重试之类的情况可能会导致消息重复写入到 Stream 中。请注意,启用幂等性需要确保 max.in.flight.requests.per.connection小于或等于5，
        // retries 大于等于0，并且ack必须设置为all 。如果这些值不是由用户明确设置的，那么将自动选择合适的值。如果设置了不兼容的值，则将抛出一个ConfigException的异常。
        props.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, true);
        // 设置事务属性时ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG会默认为true，自定义的template会被移除掉事务开启属性
        props.put(ProducerConfig.TRANSACTIONAL_ID_CONFIG, CLIENT_ID_CONFIG);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, KafkaConstant.SerializerType.STRING.getSerializer());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaConstant.SerializerType.STRING.getSerializer());
        // See https://kafka.apache.org/documentation/#producerconfigs for more properties
        return props;
    }

    public static void main(String[] args) {
//        Map<String, Object> props = new HashMap<>();
//        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, KafkaConstant.SerializerType.BYTE_ARRAY.getSerializer());
//        props.putAll(producerConfigs());
//        KafkaTemplate<Integer, String> kafkaTemplate = getTemplate(null, String.class, null);
//        KafkaConstant.SerializerType serializerType = KafkaConstant.SerializerType.getSerializerByClass(KafkaProducerCreateFactory.class);
//        System.out.printf(props.toString());
    }

//    既引入了kafka事务，也引入了数据库的事务,事务的顺序是先提交数据库的事务，再提交kafka的事务
//    @Transactional
//    public void process(List<Thing> things) {
//        things.forEach(thing -> this.kafkaTemplate.send("topic", thing));
//        updateDb(things);
//    }
//    @Bean
//    public ChainedKafkaTransactionManager<Object, Object> chainedTm(
//            KafkaTransactionManager<String, String> ktm,
//            DataSourceTransactionManager dstm) {
//
//        return new ChainedKafkaTransactionManager<>(ktm, dstm);
//    }
//
//    @Bean
//    public DataSourceTransactionManager dstm(DataSource dataSource) {
//        return new DataSourceTransactionManager(dataSource);
//    }
//
//    @Bean
//    public ConcurrentKafkaListenerContainerFactory<?, ?> kafkaListenerContainerFactory(
//            ConcurrentKafkaListenerContainerFactoryConfigurer configurer,
//            ConsumerFactory<Object, Object> kafkaConsumerFactory,
//            ChainedKafkaTransactionManager<Object, Object> chainedTM) {
//
//        ConcurrentKafkaListenerContainerFactory<Object, Object> factory =
//                new ConcurrentKafkaListenerContainerFactory<>();
//        configurer.configure(factory, kafkaConsumerFactory);
//        factory.getContainerProperties().setTransactionManager(chainedTM);
//        return factory;
//    }

}
