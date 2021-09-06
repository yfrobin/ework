package com.yefei.ework.entity.result;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.yefei.ework.common.enums.ResultEnum;

public class CommonResult<T> {

    private boolean success;

    private String code;

    private String msg;

    private T data;


    public CommonResult() {
    }

    public CommonResult(boolean success, String code, String msg, T data) {
        this.success = success;
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <T> CommonResult<T> ok(T data) {
        return new CommonResult<>(true, ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getDesc(), data);
    }

    public static <T> CommonResult<T> error(ResultEnum resultEnum) {
        return new CommonResult<>(true, resultEnum.getCode(), resultEnum.getDesc(), null);
    }

    public static <T> CommonResult<T> error(ResultEnum resultEnum, String errMgs) {
        return new CommonResult<>(true, resultEnum.getCode(), errMgs, null);
    }

    public static <T> CommonResult<T> error(String code, String errMgs) {
        return new CommonResult<>(true,code, errMgs, null);
    }

    public String toJsonString(){
       return JSON.toJSONString(this, SerializerFeature.WriteMapNullValue);
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
