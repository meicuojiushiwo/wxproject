package com.nbh.wxprojectadmin.plugin.kafka;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.util.CollectionUtils;
import plugin.kafka.KafkaConstant;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Configuration
public class KafkaConsumerCreateFactory {


    /**
     * 字符串数组生产者
     *
     * @return
     */
    @Bean(name = "stringTemplate")
    public KafkaTemplate<String, String> stringTemplate() {
        return getTemplate(String.class, String.class, null);
    }

    /**
     * 字节数组生产者
     *
     * @return
     */
    @Bean(name = "bytesTemplate")
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
        return new KafkaTemplate<>(producerFactory(producerConfigs()), configMap);
    }


    private <K, V> ProducerFactory<K, V> producerFactory(Map<String, Object> configMap) {
        DefaultKafkaProducerFactory defaultKafkaProducerFactory = new DefaultKafkaProducerFactory<>(configMap);
        // 为每个线程创建一个单独的生产者
        // 如果producerPerThread是true，用户代码必须调用closeThreadBoundProducer()时，不再需要制作的工厂。这将物理上关闭生产者并将其从中移除ThreadLocal。reset()或destroy()将不会清理这些生产者。
        defaultKafkaProducerFactory.setProducerPerThread(true);
        return defaultKafkaProducerFactory;
    }

    private Map<String, Object> producerConfigs() {
        Map<String, Object> props = new HashMap<>();
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


}
