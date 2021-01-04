package com.nbh.wxprojectcore.plugin.kafka;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.*;
import org.apache.kafka.common.utils.Bytes;

import java.nio.ByteBuffer;
import java.util.Objects;

/**
 * kafka 中间件枚举类
 */
@Slf4j
public class KafkaConstant {

    /**
     * 生产者提交信息后确认配置
     */
    @Getter
    @AllArgsConstructor
    public enum IsolationLevelType {

        READ_UNCOMMITTED("read_uncommitted", "consumer.poll（）将返回所有消息，甚至是已中止的事务性消息。非事务性消息将以两种方式无条件返回。消息将始终以偏移顺序返回"),
        READ_COMMITTED("read_committed", "consumer.poll（）将仅返回直到最后一个稳定偏移量（LSO）的消息，该偏移量小于第一个未完成交易的偏移量。特别是，属于正在进行的事务的消息之后出现的任何消息都将被保留，直到相关事务完成为止"),
        ;

        private String code;
        private String desc;

    }

    /**
     * 生产者提交信息后确认配置
     */
    @Getter
    @AllArgsConstructor
    public enum AcksType {

        N0("0", "如果设置为0，则 producer 不会等待服务器的反馈。该消息会被立刻添加到 socket buffer 中并认为已经发送完成。在这种情况下，服务器是否收到请求是没法保证的，并且参数retries也不会生效（因为客户端无法获得失败信息）。每个记录返回的 offset 总是被设置为-1"),
        N1("1", "如果设置为1，leader节点会将记录写入本地日志，并且在所有 follower 节点反馈之前就先确认成功。在这种情况下，如果 leader 节点在接收记录之后，并且在 follower 节点复制数据完成之前产生错误，则这条记录会丢失"),
        ALL("all", "如果设置为all，这就意味着 leader 节点会等待所有同步中的副本确认之后再确认这条记录是否发送完成。只要至少有一个同步副本存在，记录就不会丢失。这种方式是对请求传递的最有效保证。acks=-1与acks=all是等效的。"),
        N_1("-1", "acks=-1与acks=all是等效的。"),
        ;

        private String code;
        private String desc;

    }

    /**
     * 自动偏移量重置类型
     */
    @Getter
    @AllArgsConstructor
    public enum AutoOffsetResetType {

        EARLIEST("earliest", "将偏移量自动重置为最早的偏移量"),
        LATEST("latest", "自动将偏移量重置为最新偏移量"),
        NONE("none", "如果未找到消费者组的先前偏移量，则向消费者抛出异常"),
        ANYTHING_ELSE("anything else", "向消费者抛出异常。"),
        ;

        private String code;
        private String desc;

    }

    /**
     * 消息日志压缩类型
     */
    @Getter
    @AllArgsConstructor
    public enum CompressionType {

        ZSTD("zstd", "它可以将压缩速度交换为更高的压缩比率（压缩速度与压缩比率的权衡可以通过小增量来配置），反之亦然"),
        GZIP("gzip", "减少文件大小有两个明显的好处，一是可以减少存储空间，二是通过网络传输文件时，可以减少传输的时间。"),
        SNAPPY("snappy", "Snappy 是一个 C++ 的用来压缩和解压缩的开发包。其目标不是最大限度压缩或者兼容其他压缩格式，而是旨在提供高速压缩速度和合理的压缩率。Snappy 比 zlib 更快，但文件相对要大 20% 到 100%。在 64位模式的 Core i7 处理器上，可达每秒 250~500兆的压缩速度"),
        LZ4("lz4", "LZ4 是一种无损压缩算法，压缩速度为每核心 400 MB/s（0.16 字节/周期）。它拥有速度极快的解码器，速度为每核心多 GB/s（0.71 字节/周期）。此外，一种称为 LZ4_HC 的高压缩率衍生产品可用于交易可定制的 CPU 时间以实现高压缩率。"),
        PRODUCER("producer", "'producer'意味着压缩类型由'producer'决定"),
        UNCOMPRESSED("uncompressed", "不压缩"),
        ;

        private String code;
        private String desc;

    }

    /**
     * 序列化枚举类
     */
    @Getter
    @AllArgsConstructor
    public enum SerializerType {

        STRING(String.class.getName(), StringDeserializer.class, StringSerializer.class),
        DOUBLE(Double.class.getName(), DoubleDeserializer.class, DoubleSerializer.class),
        FLOAT(Float.class.getName(), FloatDeserializer.class, FloatSerializer.class),
        INTEGER(Integer.class.getName(), IntegerDeserializer.class, IntegerSerializer.class),
        LONG(Long.class.getName(), LongDeserializer.class, LongSerializer.class),
        SHORT(Short.class.getName(), ShortDeserializer.class, StringSerializer.class),
        BYTES(Bytes.class.getName(), BytesDeserializer.class, BytesSerializer.class),
        BYTE_ARRAY(byte[].class.getName(), ByteArrayDeserializer.class, ByteArraySerializer.class),
        BYTE_BUFFER(ByteBuffer.class.getName(), ByteBufferDeserializer.class, ByteBufferSerializer.class),
        UUID(java.util.UUID.class.getName(), UUIDDeserializer.class, UUIDSerializer.class),
        ;

        private String code;
        private Class<? extends Deserializer> deserializer;
        private Class<? extends Serializer> serializer;

        public static SerializerType getSerializerByClass(Class clazz) {
            if (Objects.isNull(clazz)) {
                return null;
            }
            try {
                for (SerializerType serializerType : SerializerType.values()) {
                    if (serializerType.getCode().equalsIgnoreCase(clazz.getName())) {
                        return serializerType;
                    }
                }
            } catch (IllegalArgumentException e) {
                log.error("{} not found exception :", clazz.getName(), e);
                return null;
            }
            return null;
        }
    }
}
