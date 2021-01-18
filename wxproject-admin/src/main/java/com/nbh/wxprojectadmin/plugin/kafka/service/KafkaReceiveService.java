package com.nbh.wxprojectadmin.plugin.kafka.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.listener.adapter.ConsumerRecordMetadata;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * kafka消息处理层
 */
@Component
public class KafkaReceiveService {

    /**
     * 该示例为单个record接收处理
     *
     * @param dateStr
     * @return
     */
    // todo 检查KafkaListener是不是会并发执行
    @KafkaListener(id = "admin_test", topics = "test", clientIdPrefix = "admin_test", containerFactory = "kafkaListenerContainerFactory",
            autoStartup = "true"
//            , errorHandler = "validationErrorHandler"
    )
    // sendTo的作用是把当前方法的结果转发给另外的topic，即使当前方法不存在返回值
    @SendTo("test2")
    public String testListener(@Payload @Validated String dateStr) {
        System.out.println(dateStr);
        return dateStr;
    }

    /**
     * 该示例为单个record接收处理
     */
    @KafkaListener(id = "admin_test2", topics = "test2", clientIdPrefix = "admin_test2", containerFactory = "kafkaListenerContainerFactory",
            autoStartup = "true"
//            , errorHandler = "validationErrorHandler"
    )
    public ConsumerRecordMetadata test2Listener(ConsumerRecord<Integer, String> record) {
        System.out.println(record);
        return null;
    }

    /**
     * 批量消费
     *
     * @param record
     * @return
     */
    @KafkaListener(id = "admin_test3", topics = "test3", clientIdPrefix = "admin_test3", containerFactory = "batchKafkaListenerContainerFactory",
            autoStartup = "true"
//            , errorHandler = "validationErrorHandler"
    )
    public ConsumerRecordMetadata test3Listener(List<ConsumerRecord<Integer, String>> record) {
        System.out.println(record);
        return null;
    }


    /**
     * 该示例为批量record接收处理
     *
     * @param list
     */
//    @KafkaListener(id = "admin_batch_test", topics = "test2", clientIdPrefix = "admin_batch_test", containerFactory = "batchKafkaListenerContainerFactory",
//            autoStartup = "true"
////            ,errorHandler = "validationErrorHandler"
//    )
//    public List<ConsumerRecord<String, String>> testBatchListener(List<ConsumerRecord<String, String>> list) {
//        System.out.println(JSON.toJSONString(list));
//        return list;
//    }
}
