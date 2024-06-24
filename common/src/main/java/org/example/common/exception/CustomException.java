package org.example.common.exception;

import lombok.Data;
import org.example.common.AppHttpCodeEnum;

@Data
public class CustomException extends RuntimeException {
    int code;
    String msg;
    private AppHttpCodeEnum appHttpCodeEnum;
    public CustomException(AppHttpCodeEnum appHttpCodeEnum) {
        this.appHttpCodeEnum = appHttpCodeEnum;
    }

    public CustomException(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
