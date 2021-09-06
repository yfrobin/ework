package com.yefei.ework.core.security;

import cn.hutool.core.util.StrUtil;
import com.yefei.ework.common.enums.ResultEnum;
import com.yefei.ework.entity.result.CommonResult;
import com.yefei.ework.utils.JwtProperties;
import com.yefei.ework.utils.JwtProvider;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>
 * JWT登录过滤器
 * </p>
 * <p>
 * 拿到请求头中的token解析出其中的用户信息，
 * 将用户信息传给下一条过滤器，
 * 拿到上下文对象赋值到上下文。
 * <p>
 *
 * @author 和耳朵
 * @since 2020-06-30
 */
@Slf4j
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private JwtProvider jwtProvider;
    @Autowired
    private JwtProperties jwtProperties;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws ServletException, IOException {
        log.info("JWT过滤器通过校验请求头token进行自动登录...");

        // 拿到Authorization请求头内的信息
        String authToken = jwtProvider.getToken(request);

        // 判断一下内容是否为空
        if (StrUtil.isNotEmpty(authToken) && authToken.startsWith(jwtProperties.getTokenPrefix())) {
            // 去掉token前缀(Bearer )，拿到真实token
            authToken = authToken.substring(jwtProperties.getTokenPrefix().length());

            // 拿到token里面的登录账号
            String loginAccount = jwtProvider.getSubjectFromToken(authToken);

            if (StringUtils.isBlank(loginAccount)) {
                response.setContentType("application/json;charset=UTF-8");
                response.getWriter().write(CommonResult.error(ResultEnum.SYSTEM_ERROR, "token错误或已过期，请重新登录").toJsonString());

                return;
            }

            if (SecurityContextHolder.getContext().getAuthentication() == null || SecurityContextHolder.getContext().getAuthentication().getPrincipal() == null) {
                response.setContentType("application/json;charset=UTF-8");
                response.getWriter().write(CommonResult.error(ResultEnum.SYSTEM_ERROR, "获取用户信息失败").toJsonString());

                return;
            }

            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            // 拿到用户信息后验证用户信息与token
            if (jwtProvider.validateToken(authToken, userDetails)) {

                // 组装authentication对象，构造参数是Principal Credentials 与 Authorities
                // 后面的拦截器里面会用到 grantedAuthorities 方法
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());

                // 将authentication信息放入到上下文对象中
                SecurityContextHolder.getContext().setAuthentication(authentication);

                log.info("JWT过滤器通过校验请求头token自动登录成功, user : {}", userDetails.getUsername());

            } else {
                response.setContentType("application/json;charset=UTF-8");
                response.getWriter().write(CommonResult.error(ResultEnum.SYSTEM_ERROR, "token错误，请重新登录").toJsonString());

                return;
            }
        }
        chain.doFilter(request, response);
    }
}