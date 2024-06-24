package org.example.sys_user;

import org.example.common.exception.ExceptionCatch;
import org.example.rediscommon.config.RedisConifg;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@EnableFeignClients
@Import({RedisConifg.class, ExceptionCatch.class})
public class SysUserApplication {
    public static void main(String[] args) {
        SpringApplication.run(SysUserApplication.class, args);
    }

}
