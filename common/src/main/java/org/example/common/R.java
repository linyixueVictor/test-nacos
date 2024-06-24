package org.example.common;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class R<T> {
    private int code;
    private String msg;
    private T data;

    public R(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static<T> R<T> errorResult(AppHttpCodeEnum enums){
        return setAppHttpCodeEnum(enums.getCode(), enums.getMsg(), null);
    }

    public static<T> R<T> okResult(AppHttpCodeEnum enums, T data){
        return setAppHttpCodeEnum(enums.getCode(),enums.getMsg(), data);
    }

    public static<T> R<T> okResult(AppHttpCodeEnum enums){
        return setAppHttpCodeEnum(enums.getCode(),enums.getMsg(), null);
    }

    private static<T> R<T> setAppHttpCodeEnum(int code, String msg, T data){
        return new R<T>(code, msg, data);
    }
}
