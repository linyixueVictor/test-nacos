package org.example.gateway.exception;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.example.common.AppHttpCodeEnum;
import org.example.common.R;
import org.example.common.exception.CustomException;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Configuration  //控制器增强类
@Slf4j
public class ExceptionCatch implements ErrorWebExceptionHandler {
    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
        ServerHttpResponse response = exchange.getResponse();
        if (response.isCommitted()) {
            return Mono.error(ex);
        }
        AppHttpCodeEnum httpCodeEnum = null;
        if (ex instanceof CustomException) {
            httpCodeEnum = ((CustomException) ex).getAppHttpCodeEnum();
        } else if (ex instanceof Exception) {
            httpCodeEnum = AppHttpCodeEnum.ERROR;
        }
        // 设置返回值类型为json
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        //设置返回编码
        if (ex instanceof ResponseStatusException) {
            response.setStatusCode(((ResponseStatusException) ex).getStatus());
        }
        AppHttpCodeEnum finalHttpCodeEnum = httpCodeEnum;
        return response.writeWith(Mono.fromSupplier(() -> {
            DataBufferFactory bufferFactory = response.bufferFactory();
            try {
                //writeValueAsBytes 组装错误响应结果
                return bufferFactory.wrap(JSONObject.toJSONBytes(R.errorResult(finalHttpCodeEnum)));
            } catch (Exception e) {
                log.error("Error writing response", ex);
                return bufferFactory.wrap(new byte[0]);
            }
        }));
    }
}
