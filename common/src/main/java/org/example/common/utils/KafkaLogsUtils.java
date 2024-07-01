package org.example.common.utils;

import com.alibaba.fastjson.JSON;
import org.example.common.consts.AppHttpCodeEnum;
import org.example.common.consts.KafkaTopic;
import org.example.common.module.SysOperateLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class KafkaLogsUtils {
    @Autowired
    private KafkaTemplate kafkaTemplate;

    public void sendLogs(String userName, String operation, AppHttpCodeEnum httpCodeEnum) {
        SysOperateLog logs = new SysOperateLog();
        logs.setStatus("执行失败");
        logs.setUserName(userName);
        logs.setOperation(operation);
        logs.setMessage(httpCodeEnum.getMsg());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        logs.setOperateTime(sdf.format(new Date()));
        kafkaTemplate.send(KafkaTopic.OPERATE_LOGS_TOPIC, JSON.toJSONString(logs));
    }
}
