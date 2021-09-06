package com.yefei.ework.common.enums;

public enum ResultEnum {

    SUCCESS("00000", "请求成功"),

    UNAUTHORIZED("00002", "认证失败"),

    FORBIDDEN("00003", "没有权限"),

    USER_NOT_EXIST("00004", "用户不存在"),

    PASSWORD_ERROR("00005", "密码错误"),

    SYSTEM_ERROR("99999", "请求异常"),

    ;


    private String code;

    private String desc;

    ResultEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
