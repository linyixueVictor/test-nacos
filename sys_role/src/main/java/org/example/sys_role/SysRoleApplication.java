package org.example.sys_role;

import org.example.common.exception.ExceptionCatch;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(ExceptionCatch.class)
public class SysRoleApplication {

    public static void main(String[] args) {
        SpringApplication.run(SysRoleApplication.class, args);
    }

}
