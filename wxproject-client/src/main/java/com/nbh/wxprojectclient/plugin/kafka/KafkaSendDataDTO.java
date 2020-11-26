package com.nbh.wxprojectclient.plugin.kafka;

import lombok.Data;

/**
 * kafka数据发送对象
 *
 * @param <K, V>
 */
@Data
public class KafkaSendDataDTO<K, V> {
    /**
     * 需要发送的键值
     */
    private V keyData;
    /**
     * 需要发送的数据
     */
    private V valueData;
    /**
     * 超时时间,默认时间为10秒（s）
     */
    private Long timeout = 10L;
    /**
     * 数据接收主题
     */
    private String topic;
    /**
     * 数据接收分区
     */
    private Integer partition;
}
