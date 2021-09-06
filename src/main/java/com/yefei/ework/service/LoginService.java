package com.yefei.ework.service;

import com.yefei.ework.core.security.AccessToken;
import com.yefei.ework.entity.request.LoginRequest;
import com.yefei.ework.entity.result.CommonResult;

public interface LoginService {
    CommonResult<AccessToken> login(LoginRequest request);
}
