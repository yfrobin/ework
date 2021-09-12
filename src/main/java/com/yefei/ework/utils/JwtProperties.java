package com.yefei.ework.utils;

import org.springframework.stereotype.Component;

@Component
//@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {

    /**
     * 密匙
     */
    private String apiSecretKey = "JWT_SECRET_KEY";

    /**
     * 过期时间-单位秒
     */
    private Long expirationTime = 1800L;

    /**
     * 默认存放token的请求头
     */
    private String requestHeader = "Authorization";

    /**
     * 默认token前缀
     */
    private String tokenPrefix = "Bearer ";


    public String getApiSecretKey() {
        return apiSecretKey;
    }

    public void setApiSecretKey(String apiSecretKey) {
        this.apiSecretKey = apiSecretKey;
    }

    public Long getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(Long expirationTime) {
        this.expirationTime = expirationTime;
    }

    public String getRequestHeader() {
        return requestHeader;
    }

    public void setRequestHeader(String requestHeader) {
        this.requestHeader = requestHeader;
    }

    public String getTokenPrefix() {
        return tokenPrefix;
    }

    public void setTokenPrefix(String tokenPrefix) {
        this.tokenPrefix = tokenPrefix;
    }
}