package com.yefei.ework.controller;


import com.yefei.ework.core.security.AccessToken;
import com.yefei.ework.entity.request.LoginRequest;
import com.yefei.ework.entity.result.CommonResult;
import com.yefei.ework.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class LoginController {



    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public CommonResult<AccessToken> login(@RequestBody LoginRequest request) {

       return loginService.login(request);

    }

}
