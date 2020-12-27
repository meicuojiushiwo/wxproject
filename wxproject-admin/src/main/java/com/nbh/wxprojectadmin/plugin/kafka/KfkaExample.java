//package com.nbh.wxprojectadmin.plugin.kafka;
//
//import javafx.application.Application;
//import org.apache.kafka.clients.admin.NewTopic;
//import org.springframework.boot.ApplicationRunner;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.kafka.ConcurrentKafkaListenerContainerFactoryConfigurer;
//import org.springframework.context.annotation.Bean;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.datasource.DataSourceTransactionManager;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
//import org.springframework.kafka.config.TopicBuilder;
//import org.springframework.kafka.core.ConsumerFactory;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.kafka.transaction.ChainedKafkaTransactionManager;
//import org.springframework.kafka.transaction.KafkaTransactionManager;
//import org.springframework.stereotype.Component;
//
//import javax.sql.DataSource;
//
//
//public class KfkaExample {
//
//    public static void main(String[] args) {
//        SpringApplication.run(Application.class, args);
//    }
//
//    @Bean
//    public ApplicationRunner runner(KafkaTemplate<String, String> template) {
//        return args -> template.executeInTransaction(t -> t.send("topic1", "test"));
//    }
//
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
//
//    @Component
//    public static class Listener {
//
//        private final JdbcTemplate jdbcTemplate;
//
//        private final KafkaTemplate<String, String> kafkaTemplate;
//
//        public Listener(JdbcTemplate jdbcTemplate, KafkaTemplate<String, String> kafkaTemplate) {
//            this.jdbcTemplate = jdbcTemplate;
//            this.kafkaTemplate = kafkaTemplate;
//        }
//
//        @KafkaListener(id = "group1", topics = "topic1")
//        public void listen1(String in) {
//            this.kafkaTemplate.send("topic2", in.toUpperCase());
//            this.jdbcTemplate.execute("insert into mytable (data) values ('" + in + "')");
//        }
//
//        @KafkaListener(id = "group2", topics = "topic2")
//        public void listen2(String in) {
//            System.out.println(in);
//        }
//
//    }
//
//    @Bean
//    public NewTopic topic1() {
//        return TopicBuilder.name("topic1").build();
//    }
//
//    @Bean
//    public NewTopic topic2() {
//        return TopicBuilder.name("topic2").build();
//    }
//}
