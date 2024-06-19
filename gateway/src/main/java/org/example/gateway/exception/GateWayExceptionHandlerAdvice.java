package org.example.gateway.exception;

import lombok.extern.slf4j.Slf4j;
import org.example.common.R;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;


@Slf4j
@Component
public class GateWayExceptionHandlerAdvice {

    @ExceptionHandler(value = {GateWayException.class})
    public R handle(GateWayException ex) {
        log.error("网关异常code:{},msg:{}", ex.getCode(),ex.getMessage());
        return R.fail(500, ex.getMessage());
    }

    @ExceptionHandler(value = {Throwable.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public R handle(Throwable throwable) {

        if(throwable instanceof GateWayException) {
            return handle((GateWayException) throwable);
        }else {
            return R.error();
        }
    }
}
