package com.nbh.wxprojectclient.plugin.kafka.service;

import com.alibaba.fastjson.JSON;
import com.nbh.wxprojectclient.plugin.kafka.dto.KafkaSendDataDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaProducerException;
import org.springframework.kafka.core.KafkaSendCallback;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.concurrent.ListenableFuture;

@Slf4j
@Service("kafkaSendService")
public class KafkaSendService<K, V> {

    @Autowired
    private KafkaTemplate stringTemplate;


    /**
     * 同步发送消息
     *
     * @param data
     */
    public void sendMsgSync(KafkaSendDataDTO<K, V> data) {
//        stringTemplate.setProducerListener();
    }

    /**
     * 异步发送消息
     * @param data
     */
    public void sendMsg(KafkaSendDataDTO<K, V> data) {

    }

    public static void main(String[] args) {
    }

//    @Transactional
    public void test() {
        //        ListenableFuture<SendResult<String, String>> future = this.stringTemplate.send("test", "foo1");
//        // 添加回调函数异步处理,kafka2.5版本前
//        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
//            @Override
//            public void onFailure(Throwable throwable) {
//                ProducerRecord producerRecord = ((KafkaProducerException) throwable).getProducerRecord();
////                handleFailure(data, record, ex);
//            }
//
//            @Override
//            public void onSuccess(SendResult<String, String> stringStringSendResult) {
////                handleSuccess(data);
//            }
//        });
//        // 同步处理
//        try {
//            this.stringTemplate.send("test", "foo2").get(10, TimeUnit.SECONDS);
////            handleSuccess(data);
//        }
//        catch (ExecutionException e) {
////            handleFailure(data, record, e.getCause());
//        }
//        catch (TimeoutException | InterruptedException e) {
////            handleFailure(data, record, e);
//        }
//        this.template.send("test", "foo3");
//        latch.await(60, TimeUnit.SECONDS);
        try {
            // kafka2.5版本后异步
            ListenableFuture<SendResult<String, String>> future = this.stringTemplate.send("test2", "testData");
            // 添加回调函数异步处理
            future.addCallback(new KafkaSendCallback<String, String>() {
                @Override
                public void onSuccess(SendResult<String, String> stringStringSendResult) {
    //                handleSuccess(data, record, e.getCause());
                    System.out.printf(JSON.toJSONString(stringStringSendResult));
                }
                @Override
                public void onFailure(KafkaProducerException e) {
    //                handleFailure(data);
                    System.out.printf(JSON.toJSONString(e));
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(stringTemplate);
//        System.out.println(bytesTemplate);
//        System.out.println(kafkaTemplate);
        log.info("All received");
    }

    //    /**
//     * 非阻塞（异步） 发送消息
//     * @param data
//     */
//    public void sendToKafka(final MyOutputData data) {
//        final ProducerRecord<String, String> record = createRecord(data);
//
//        ListenableFuture<SendResult<Integer, String>> future = template.send(record);
//        future.addCallback(new KafkaSendCallback<SendResult<Integer, String>>() {
//
//            @Override
//            public void onSuccess(SendResult<Integer, String> result) {
////                handleSuccess(data);
//            }
//
//            @Override
//            public void onFailure(KafkaProducerException ex) {
////                handleFailure(data, record, ex);
//            }
//
//        });
//    }
//
//    /**
//     * 阻塞（同步） 发送消息
//     * @param data
//     */
//    public void sendToKafka(final MyOutputData data) {
//        final ProducerRecord<String, String> record = createRecord(data);
//
//        try {
//            template.send(record).get(10, TimeUnit.SECONDS);
////            handleSuccess(data);
//        }
//        catch (ExecutionException e) {
////            handleFailure(data, record, e.getCause());
//        }
//        catch (TimeoutException | InterruptedException e) {
////            handleFailure(data, record, e);
//        }
//    }
}
