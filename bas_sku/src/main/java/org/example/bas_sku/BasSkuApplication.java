package org.example.bas_sku;

import org.example.rediscommon.config.RedisConifg;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(RedisConifg.class)
public class BasSkuApplication {

    public static void main(String[] args) {
        SpringApplication.run(BasSkuApplication.class, args);
    }

}
