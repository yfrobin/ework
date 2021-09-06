package com.yefei.ework.core.security;

import org.springframework.security.core.AuthenticationException;

public class SecurityException extends AuthenticationException {

    private String code;

    private String errMsg;

    public SecurityException(String code, String errMsg) {
        super(errMsg);
        this.code = code;
        this.errMsg = errMsg;
    }

    public SecurityException(String code, String errMsg, Throwable cause) {
        super(errMsg, cause);
        this.code = code;
        this.errMsg = errMsg;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }
}