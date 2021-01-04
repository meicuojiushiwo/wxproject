package com.nbh.wxprojectclient.plugin.kafka.exception;

/**
 *  kafka生产者消息入队异常
 */
public class KafkaAddQueueException extends Exception{

    // 对某个包下的类进行一场拦截
//    @Pointcut("execution(* com.xxx.service..*.*(..))")


    public KafkaAddQueueException(String message, Throwable cause) {
        super(message, cause);
    }
}
