package org.example.common.exception;

import lombok.Data;

@Data
public class CustomException extends RuntimeException {
    private int code;
    private String msg;
    private String exMsg;
    public CustomException(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    public CustomException(int code, String msg, String exMsg) {
        this.code = code;
        this.msg = msg;
        this.exMsg = exMsg;
    }
}
