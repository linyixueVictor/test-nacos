package org.example.doc_order;

import org.example.common.exception.ExceptionCatch;
import org.example.common.utils.KafkaLogsUtils;
import org.example.rediscommon.config.RedisConifg;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({RedisConifg.class, ExceptionCatch.class, KafkaLogsUtils.class})
public class DocOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(DocOrderApplication.class, args);
    }

}
