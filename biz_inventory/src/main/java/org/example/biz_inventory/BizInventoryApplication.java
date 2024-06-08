package org.example.biz_inventory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.locks.ReentrantLock;

@SpringBootApplication
@EnableFeignClients
public class BizInventoryApplication {

    public static void main(String[] args) {
        SpringApplication.run(BizInventoryApplication.class, args);
    }
    @Bean
    public ReentrantLock ReentrantLock() {
        return new ReentrantLock();
    }

}
