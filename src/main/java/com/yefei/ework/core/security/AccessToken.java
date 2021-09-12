package com.yefei.ework.core.security;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class AccessToken {

    /**
     * 用户名
     */
    private String loginAccount;

    /**
     * token
     */
    private String token;

    /**
     * 过期时间
     */
    @JsonFormat
    private Date expirationTime;
}