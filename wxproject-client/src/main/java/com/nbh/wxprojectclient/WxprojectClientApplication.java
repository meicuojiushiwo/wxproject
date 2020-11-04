package com.nbh.wxprojectclient;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaProducerException;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Slf4j
@SpringBootApplication
public class WxprojectClientApplication  implements CommandLineRunner {

    @Autowired
    private KafkaTemplate template;
    private final CountDownLatch latch = new CountDownLatch(3);


    public static void main(String[] args) {
        SpringApplication.run(WxprojectClientApplication.class, args);
//        SpringApplication.run(WxprojectClientApplication.class, args).close();
    }

    /**
     * 发送数据
     * @param args
     * @throws Exception
     */
    @Override
    public void run(String... args) throws Exception {
//        this.template.send("test", "foo1");
//        this.template.send("test", "foo2");
//        this.template.send("test", "foo3");
//        latch.await(60, TimeUnit.SECONDS);
        log.info("All received");
    }

    /**
     * 消费数据
     * @param cr
     * @throws Exception
     */
//    @KafkaListener(topics = "test",groupId = "client")
    public void listen(ConsumerRecord<?, ?> cr) throws Exception {
//        log.info(cr.toString());
//        latch.countDown();
    }

    /**
     * 消费数据
     * @param cr
     * @throws Exception
     */
//    @KafkaListener(topics = "test",groupId = "client")
//    public void listen2(ConsumerRecord<?, ?> cr) throws Exception {
//        log.info(cr.toString());
//        latch.countDown();
//    }

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
