package com.nbh.wxprojectadmin.plugin.kafka;

import com.alibaba.fastjson.JSON;
import com.nbh.wxprojectcore.plugin.kafka.KafkaConstant;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.RoundRobinAssignor;
import org.apache.kafka.common.TopicPartition;
import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.Request;
import org.asynchttpclient.RequestBuilder;
import org.asynchttpclient.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.ConsumerAwareRebalanceListener;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.listener.adapter.ReplyHeadersConfigurer;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import static com.nbh.wxprojectadmin.plugin.kafka.KafkaConfig.BOOTSTRAP_SERVERS_CONFIG;
import static com.nbh.wxprojectadmin.plugin.kafka.KafkaConfig.GROUP_ID_CONFIG;
import static org.asynchttpclient.Dsl.asyncHttpClient;
import static org.asynchttpclient.Dsl.config;

@Configuration
@EnableKafka
public class KafkaConsumerCreateFactory {

    @Autowired
    private KafkaTemplate kafkaTemplate;
//    @Autowired
//    private SeekToCurrentErrorHandler seekToCurrentErrorHandler;
//    @Autowired
//    private KafkaTransactionManager kafkaTransactionManager;
    /**
     * 死信队列
     */
//    @Autowired
//    private DeadLetterPublishingRecoverer deadLetterPublishingRecoverer;

    /**
     * 批处理消息容器工厂
     *
     * @param <K>
     * @param <V>
     * @return
     */
    @Bean
    public <K, V> KafkaListenerContainerFactory batchKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<K, V> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(getConsumerFactory(defaultConsumerConfigs()));
        factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.BATCH);
        // 批处理
        factory.setBatchListener(true);
        return factory;
    }

    /**
     * 单个消息容器处理
     *
     * @param <K>
     * @param <V>
     * @return
     */
    @Bean
    public <K, V> KafkaListenerContainerFactory kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<K, V> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(getConsumerFactory(defaultConsumerConfigs()));
        factory.setConcurrency(3);
        //  重试传送
//        factory.setRetryTemplate(new RetryTemplate());
//        factory.setStatefulRetry(true);
//        factory.setRecoveryCallback(context -> {
//            deadLetterPublishingRecoverer.accept((ConsumerRecord<?, ?>) context.getAttribute("record"),
//                    (Exception) context.getLastThrowable());
//            return null;
//        });
        factory.getContainerProperties().setEosMode(ContainerProperties.EOSMode.BETA);
        // todo 确定事务是否生效
        // 启动事务
//        factory.getContainerProperties().setTransactionManager(kafkaTransactionManager);
        factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.RECORD);
        // 批量重试会有问题，但是可以用批量恢复替代（Recovering Batch Error Handler：该异常恢复处理不能与事务同时调用）
//        factory.setErrorHandler(seekToCurrentErrorHandler);
        // 设置在@KafkaListener下添加@sendTo（转发注解）时需要提供一个用于答复的容器
        factory.setReplyTemplate(kafkaTemplate);
        factory.setReplyHeadersConfigurer(new ReplyHeadersConfigurer() {

            @Override
            public boolean shouldCopy(String headerName, Object headerValue) {
                return false;
            }

            @Override
            public Map<String, Object> additionalHeaders() {
                return Collections.singletonMap("qux", "fiz");
            }

        });
        factory.getContainerProperties().setCommitRetries(3);
        // 分区撤销时，消费者重新平衡
        factory.getContainerProperties().setConsumerRebalanceListener(new ConsumerAwareRebalanceListener() {


            @Override
            public void onPartitionsRevokedBeforeCommit(Consumer<?, ?> consumer, Collection<TopicPartition> partitions) {
                // acknowledge any pending Acknowledgments (if using manual acks)
                System.out.println(JSON.toJSONString(consumer));
            }

            @Override
            public void onPartitionsRevokedAfterCommit(Consumer<?, ?> consumer, Collection<TopicPartition> partitions) {
                // ...
                // 存储消费者的提交信息
//                store(consumer.position(partition));
                // ...
                System.out.println(JSON.toJSONString(consumer));
            }

            @Override
            public void onPartitionsAssigned(Collection<TopicPartition> partitions) {
                // ...
                // 开始消费之前查找上一次消费的节点
//                consumer.seek(partition, offsetTracker.getOffset() + 1);
                // ...
                System.out.println(JSON.toJSONString(partitions));

            }
        });
        return factory;
    }

    /**
     * 异常处理器
     * 其他异常处理器有
     * 监听异常处理器（Listener Error Handlers）
     * 容器异常处理器（Container Error Handlers）
     * 消费监听容器异常处理器（Consumer-Aware Container Error Handlers）
     * 当前记录异常容器异常处理器（Seek To Current Container Error Handlers）
     * 容器停止异常处理器（Container Stopping Error Handlers）
     * 以下处理器不能与事务公用
     * 批量重试异常处理器（Retrying Batch Error Handler）
     * 批量恢复异常处理器（Recovering Batch Error Handler）
     * <p>
     * 消费者消息记录消费的进行三次提交偏移量，如果三次提交失败则进入死信队列
     *
     * @return
     */
//    @Bean
//    public SeekToCurrentErrorHandler seekToCurrentErrorHandler() {
//        SeekToCurrentErrorHandler seekToCurrentErrorHandler = new SeekToCurrentErrorHandler((record, exception) -> {
//            // recover after 3 failures, woth no back off - e.g. send to a dead-letter topic
//            // 自定义恢复程序（BiConsumer）
//        }, new FixedBackOff(1000L, 2L));
//        // 不可重试异常
//        seekToCurrentErrorHandler.removeNotRetryableException(DeserializationException.class);
//        seekToCurrentErrorHandler.removeNotRetryableException(MessageConversionException.class);
//        seekToCurrentErrorHandler.removeNotRetryableException(ConversionException.class);
//        seekToCurrentErrorHandler.removeNotRetryableException(MethodArgumentResolutionException.class);
//        seekToCurrentErrorHandler.removeNotRetryableException(NoSuchMethodException.class);
//        seekToCurrentErrorHandler.removeNotRetryableException(ClassCastException.class);
//        seekToCurrentErrorHandler.removeNotRetryableException(IllegalArgumentException.class);
//        return seekToCurrentErrorHandler;
//    }
    @Bean
    public ConsumerFactory getConsumerFactory(Map<String, Object> configMap) {
        DefaultKafkaConsumerFactory defaultKafkaConsumerFactory = new DefaultKafkaConsumerFactory<>(configMap);
        return defaultKafkaConsumerFactory;
    }

    private Map<String, Object> defaultConsumerConfigs() {
        Map<String, Object> props = new HashMap<>();
        // 为每个主题的每个分区提供一个线程，搭配容器的Concurrency使用
        props.put(ConsumerConfig.GROUP_ID_CONFIG, GROUP_ID_CONFIG);
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS_CONFIG);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, KafkaConstant.SerializerType.STRING.getDeserializer());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, KafkaConstant.SerializerType.STRING.getDeserializer());
        // 最小提取字节
        props.put(ConsumerConfig.FETCH_MIN_BYTES_CONFIG, 1);
//         最大分区提前字节
        props.put(ConsumerConfig.MAX_PARTITION_FETCH_BYTES_CONFIG, 1 * 1024 * 1024);
//         心跳时间，30s一次，最好设置为session的三分之一
        props.put(ConsumerConfig.HEARTBEAT_INTERVAL_MS_CONFIG, 30 * 60);
//         session超时时间
        props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, 5 * 30 * 60);
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, KafkaConstant.AutoOffsetResetType.EARLIEST.getCode());
        props.put(ConsumerConfig.EXCLUDE_INTERNAL_TOPICS_CONFIG, true);
        props.put(ConsumerConfig.ISOLATION_LEVEL_CONFIG, KafkaConstant.IsolationLevelType.READ_COMMITTED.getCode());
//         重平衡策略
//         RoundRobinAssignor所有主题的所有分区重新平均分配到每消费者
//         RangeAssignor对于所有主题来说，单个主题的的所有分区轮询分配给消费者
        props.put(ConsumerConfig.PARTITION_ASSIGNMENT_STRATEGY_CONFIG, RoundRobinAssignor.class.getName());
//         停止自动提交
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
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
        AsyncHttpClient asyncHttpClient = asyncHttpClient(config());
        try {
            String url = "https://cn.pornhub.com/video/search?search=pronhub";
            // bound
            Future<Response> whenResponse = asyncHttpClient.prepareGet(url).execute();
            System.out.println(whenResponse.get().getResponseBody());
            // unbound
//            Request request = get("https://github.com/AsyncHttpClient/async-http-client").build();
//            Future<Response> whenResponse2 = asyncHttpClient.executeRequest(request);

//            System.out.println(whenResponse2.get().getResponseBody());

            Request mkcolRequest = new RequestBuilder("MKCOL").setUrl("http://host:port/folder1").build();
            Response response = asyncHttpClient.executeRequest(mkcolRequest).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        try (AsyncHttpClient asyncHttpClient1 = asyncHttpClient()) {
            asyncHttpClient
                    .prepareGet("http://www.example.com/")
                    .execute()
                    .toCompletableFuture()
                    .thenApply(Response::getResponseBody)
                    .thenAccept(System.out::println)
                    .join();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
