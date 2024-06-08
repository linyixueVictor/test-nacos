package org.example.sys_user.config;

import feign.Logger;
import feign.Request;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenFeignConfig {
    /**
     * 日志级别
     * @return
     * NONE【性能最佳，默认值】：不记录任何日志。
     * BASIC【适用于生产环境追踪问题】：仅记录请求方法、URL、响应状态代码以及执行时间。
     * HEADERS：记录BASIC级别的基础上，记录请求和响应的header。
     * FULL【比较适用于开发及测试环境定位问题】：记录请求和响应的header、body和元数据。
     */
    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    /**
     * 设置远程调用超时时间，包括连接超时时间和请求处理超时时间
     * @return
     */
    @Bean
    public Request.Options options() {
        return new Request.Options(3000, 5000);
    }

}
