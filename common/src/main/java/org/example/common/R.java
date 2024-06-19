package org.example.common;

import java.util.HashMap;
import java.util.Map;

public class R<T> {
    private int code;
    private String msg;
    private T data;

    public R(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public R(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static<T> R<T> error() {
        return new R<T>(999, "系统异常，请联系管理员");
    }

    public static<T> R<T> fail(int code, String msg) {
        return new R<T>(code, msg);
    }

    public static<T> R<T> ok(String msg) {
        return new R<T>(200, msg);
    }

    public static<T> R<T> ok(String msg, T data) {
        return new R<T>(200, msg, data);
    }

    public Object getData() {
        return data;
    }

    public Integer getCode() {
        return code;
    }
}
