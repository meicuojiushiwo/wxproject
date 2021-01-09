package com.nbh.wxprojectclient.plugin.kafka;

import com.google.common.collect.Lists;
import com.nbh.wxprojectcore.plugin.kafka.KafkaConstant;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.config.TopicConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

import static com.nbh.wxprojectclient.plugin.kafka.KafkaConfig.BOOTSTRAP_SERVERS_CONFIG;

/**
 * kafka主题创建类
 */
@Component
public class KafkaTopic {

    @Bean
    public KafkaAdmin admin() {
        Map<String, Object> configs = new HashMap<>();
        // 为topic的创建提供服务器地址，否则默认为本机地址（localhost/127.0.0.1:9092）创建主题
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS_CONFIG);
        return new KafkaAdmin(configs);
    }

    @Bean
    public NewTopic test() {
        return TopicBuilder.name("test")
                // 当服务器上的分区数没有以下方法设置的多是服务器会增加分区
                .partitions(3)
                // .replicas(2)
                // 0为分区编号，后面的集合为该分区的备份会放在哪个机器上，(102,107)对应kafka服务器的brokerid，
                // 每个分区对应的备份集合数要一致,使用assignReplicas方法时，replicas方法设置分区会被忽略
                .assignReplicas(0, Lists.newArrayList(102, 106))
                .assignReplicas(1, Lists.newArrayList(102, 107))
                .assignReplicas(2, Lists.newArrayList(106, 107))
                .config(TopicConfig.COMPRESSION_TYPE_CONFIG, KafkaConstant.CompressionType.LZ4.getCode())
                // 指示是否启用不在ISR集合中的副本选为领导者作为最后的手段，即使这样做可能导致数据丢失
                .config(TopicConfig.UNCLEAN_LEADER_ELECTION_ENABLE_CONFIG, "false")
                .compact()
                .build();
    }

    @Bean
    public NewTopic test2() {
        return TopicBuilder.name("test2")
                // 当服务器上的分区数没有以下方法设置的多是服务器会增加分区
                .partitions(3)
                // .replicas(2)
                // 0为分区编号，后面的集合为该分区的备份会放在哪个机器上，(102,107)对应kafka服务器的brokerid，
                // 每个分区对应的备份集合数要一致,使用assignReplicas方法时，replicas方法设置分区会被忽略
                .assignReplicas(0, Lists.newArrayList(102, 106))
                .assignReplicas(1, Lists.newArrayList(102, 107))
                .assignReplicas(2, Lists.newArrayList(106, 107))
                .config(TopicConfig.COMPRESSION_TYPE_CONFIG, KafkaConstant.CompressionType.LZ4.getCode())
                // 指示是否启用不在ISR集合中的副本选为领导者作为最后的手段，即使这样做可能导致数据丢失
                .config(TopicConfig.UNCLEAN_LEADER_ELECTION_ENABLE_CONFIG, "false")
                .compact()
                .build();
    }

    @Bean
    public NewTopic test3() {
        return TopicBuilder.name("test3")
                // 当服务器上的分区数没有以下方法设置的多是服务器会增加分区
                .partitions(3)
                // .replicas(2)
                // 0为分区编号，后面的集合为该分区的备份会放在哪个机器上，(102,107)对应kafka服务器的brokerid，
                // 每个分区对应的备份集合数要一致,使用assignReplicas方法时，replicas方法设置分区会被忽略
                .assignReplicas(0, Lists.newArrayList(102, 106))
                .assignReplicas(1, Lists.newArrayList(102, 107))
                .assignReplicas(2, Lists.newArrayList(106, 107))
                .config(TopicConfig.COMPRESSION_TYPE_CONFIG, KafkaConstant.CompressionType.LZ4.getCode())
                // 指示是否启用不在ISR集合中的副本选为领导者作为最后的手段，即使这样做可能导致数据丢失
                .config(TopicConfig.UNCLEAN_LEADER_ELECTION_ENABLE_CONFIG, "false")
                .compact()
                .build();
    }

}
