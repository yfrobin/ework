package com.yefei.ework.core.security;

import com.yefei.ework.common.enums.ResultEnum;
import com.yefei.ework.entity.result.CommonResult;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 403 Forbidden ：该HTTP状态码关于授权的，跟应用的的逻辑有关，表示客户端没有权限去访问要求资源
 */
@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(CommonResult.error(ResultEnum.FORBIDDEN).toJsonString());
    }
}