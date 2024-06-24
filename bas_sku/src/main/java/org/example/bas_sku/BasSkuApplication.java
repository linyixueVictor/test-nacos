package org.example.bas_sku;

import org.example.common.exception.ExceptionCatch;
import org.example.rediscommon.config.RedisConifg;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({RedisConifg.class, ExceptionCatch.class})
public class BasSkuApplication {

    public static void main(String[] args) {
        SpringApplication.run(BasSkuApplication.class, args);
    }

}
