package com.nbh.wxprojectclient;

import com.nbh.wxprojectclient.plugin.kafka.service.KafkaSendService;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@SpringBootApplication
public class WxprojectClientApplication implements CommandLineRunner {


//    @Autowired
//    private KafkaTemplate stringTemplate;
    @Autowired
    private KafkaSendService kafkaSendService;

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
//        stringTemplate.sendDefault("testDefault");
        kafkaSendService.test1();
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
