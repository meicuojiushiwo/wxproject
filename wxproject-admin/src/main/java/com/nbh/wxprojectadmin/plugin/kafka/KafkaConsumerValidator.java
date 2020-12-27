package com.nbh.wxprojectadmin.plugin.kafka;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListenerConfigurer;
import org.springframework.kafka.config.KafkaListenerEndpointRegistrar;
import org.springframework.kafka.listener.KafkaListenerErrorHandler;

@Configurable
@EnableKafka
public class KafkaConsumerValidator implements KafkaListenerConfigurer {

    // 注册校验器
    @Override
    public void configureKafkaListeners(KafkaListenerEndpointRegistrar registrar) {
        // todo 设置数据校验器
//        registrar.setValidator();
    }

    /**
     * 校验异常处理对象
     * @return
     */
    @Bean
    public KafkaListenerErrorHandler validationErrorHandler() {
        return (m, e) -> {
            // 校验异常处理
            return null;
        };
    }
}
