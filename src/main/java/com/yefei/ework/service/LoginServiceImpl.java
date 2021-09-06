package com.yefei.ework.service;

import com.yefei.ework.core.security.AccessToken;
import com.yefei.ework.entity.request.LoginRequest;
import com.yefei.ework.entity.result.CommonResult;
import com.yefei.ework.service.template.AbstractServiceTemplate;
import com.yefei.ework.utils.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl extends AbstractServiceTemplate implements LoginService {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtProvider jwtProvider;

    @Override
    public CommonResult<AccessToken> login(LoginRequest request) {
        return execute(() -> {

            // 1 创建UsernamePasswordAuthenticationToken
            UsernamePasswordAuthenticationToken usernameAuthentication = new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword());

            // 2 认证
            Authentication authentication = this.authenticationManager.authenticate(usernameAuthentication);

            // 3 保存认证信息
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // 4 生成自定义token
            return jwtProvider.createToken((UserDetails) authentication.getPrincipal());
        });
    }
}
