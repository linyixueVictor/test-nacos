package org.example.common;

public enum AppHttpCodeEnum {
    SUCCESS(200,"操作成功"),
    SUCCESS_GET(200, "查询成功"),
    SUCCESS_REGISTER(200, "注册成功"),
    SUCCESS_LOGIN(200, "登录成功"),
    GET_TOKENKEY_ERROR(50,"远程调用认证服务器获取Token_Key失败"),
    GET_PUBLICKEY_ERROR(51,"生成公钥异常"),
    TOKEN_EMPTY(60,"TOKEN为空"),
    TOKEN_CHECK(61,"校验TOKEN异常"),
    TOKEN_EXPIRE(62,"TOKEN已过期"),
    LOGIN_ERROR(1, "用户名或密码错误"),
    USER_NOT_EXISTS(1001, "用户不存在"),
    USER_EXISTED(1002, "用户已存在"),
    ROLE_NOT_EXISTS(1001, "角色不存在"),
    ROLE_EXISTED(1002, "角色已存在"),
    ERROR(999, "系统异常，请联系管理员");

    int code;
    String msg;

    AppHttpCodeEnum(int code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
