package com.yefei.ework.service;

import com.yefei.ework.entity.VO.UserVO;
import com.yefei.ework.entity.request.UserAddRequest;
import com.yefei.ework.entity.result.CommonResult;

public interface UserService {
    CommonResult<Boolean> add(UserAddRequest request);

    CommonResult<UserVO> getByUsername(String username);

    CommonResult<Boolean> addRole(String roleCode);
}
