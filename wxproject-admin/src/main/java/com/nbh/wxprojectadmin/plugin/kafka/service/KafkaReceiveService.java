package com.nbh.wxprojectadmin.plugin.kafka.service;

import com.alibaba.fastjson.JSON;
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
     * @param meta
     * @param param1
     * @param param2
     */
    // todo 检查KafkaListener是不是会并发执行
    @KafkaListener(id = "admin_test", topics = "test2", clientIdPrefix = "admin_test", containerFactory = "kafkaListenerContainerFactory",
            autoStartup = "true"
//            , errorHandler = "validationErrorHandler"
    )
    // sendTo的作用是把当前方法的结果转发给另外的topic，即使当前方法不存在返回值
    @SendTo("test2")
    public ConsumerRecordMetadata testListener(@Payload @Validated ConsumerRecordMetadata meta, String param1, String param2) {
        System.out.println(JSON.toJSONString(meta));
        System.out.println(param1);
        System.out.println(param2);
        return meta;
    }


    /**
     * 该示例为批量record接收处理
     *
     * @param list
     */
    @KafkaListener(id = "admin_batch_test", topics = "test2", clientIdPrefix = "admin_batch_test", containerFactory = "batchKafkaListenerContainerFactory",
            autoStartup = "true"
//            ,errorHandler = "validationErrorHandler"
    )
    public List<ConsumerRecord<String, String>> testBatchListener(List<ConsumerRecord<String, String>> list) {
        System.out.println(JSON.toJSONString(list));
        return list;
    }
}
