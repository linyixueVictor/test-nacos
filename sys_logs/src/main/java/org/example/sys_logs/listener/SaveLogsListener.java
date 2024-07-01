package org.example.sys_logs.listener;

import com.alibaba.cloud.commons.lang.StringUtils;
import com.alibaba.fastjson.JSON;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.admin.TopicDescription;
import org.example.common.consts.KafkaTopic;
import org.example.common.module.SysLoginLog;
import org.example.common.module.SysOperateLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Map;

@Component
public class SaveLogsListener {
    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private KafkaAdmin kafkaAdmin;
    @PostConstruct
    public void createTopicIfNeed() {
        Map<String, TopicDescription> describeTopicsResult = kafkaAdmin.describeTopics(KafkaTopic.OPERATE_LOGS_TOPIC);
        if (!describeTopicsResult.containsKey(KafkaTopic.OPERATE_LOGS_TOPIC)) {
            NewTopic newTopic = new NewTopic(KafkaTopic.OPERATE_LOGS_TOPIC, 1, (short) 1);
            kafkaAdmin.createOrModifyTopics(newTopic);
        }
    }

    @KafkaListener(topics = KafkaTopic.OPERATE_LOGS_TOPIC)
    public void operateLogsOnMessage(String message) {
        if (!StringUtils.isBlank(message)) {
            SysOperateLog logs = JSON.parseObject(message, SysOperateLog.class);
            mongoTemplate.save(logs);
        }
    }

    @KafkaListener(topics = KafkaTopic.LOGIN_LOGS_TOPIC)
    public void LoginLogsOnMessage(String message) {
        if (!StringUtils.isBlank(message)) {
            SysLoginLog logs = JSON.parseObject(message, SysLoginLog.class);
            mongoTemplate.save(logs);
        }
    }
}
