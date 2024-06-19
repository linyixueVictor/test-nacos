package org.example.doc_order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class DocOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(DocOrderApplication.class, args);
    }

}
