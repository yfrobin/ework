package com.yefei.ework.service;

import com.yefei.ework.entity.result.CommonResult;

public interface PermissionService {


    CommonResult<Boolean> add(String code);
}
