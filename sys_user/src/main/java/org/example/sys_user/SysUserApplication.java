package org.example.sys_user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SysUserApplication {
    public static void main(String[] args) {
        SpringApplication.run(SysUserApplication.class, args);
    }

}
