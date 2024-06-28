package org.example.doc_order;

import org.example.rediscommon.config.RedisConifg;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(RedisConifg.class)
public class DocOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(DocOrderApplication.class, args);
    }

}
