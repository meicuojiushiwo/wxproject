package com.nbh.wxprojectclient;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.core.KafkaProducerException;
import org.springframework.kafka.core.KafkaSendCallback;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Slf4j
@SpringBootApplication
public class WxprojectClientApplication implements CommandLineRunner {


    public static void main(String[] args) {
//        SpringApplication.run(WxprojectClientApplication.class, args);
//        kafkaProducerFactory.
        SpringApplication.run(WxprojectClientApplication.class, args).close();
    }

    /**
     * 发送数据
     *
     * @param args
     * @throws Exception
     */
    @Override
    public void run(String... args) throws Exception {


    }

    /**
     * 消费数据
     *
     * @param cr
     * @throws Exception
     */
//    @KafkaListener(topics = "test",groupId = "client")
    public void listen(ConsumerRecord<?, ?> cr) throws Exception {
//        log.info(cr.toString());
//        latch.countDown();
    }

//    /**
//     * 消费数据
//     * @param cr
//     * @throws Exception
//     */
//    @KafkaListener(topics = "test",groupId = "client")
//    public void listen2(ConsumerRecord<?, ?> cr) throws Exception {
//        log.info(cr.toString());
//        latch.countDown();
//    }


}
