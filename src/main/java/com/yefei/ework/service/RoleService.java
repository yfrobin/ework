package com.yefei.ework.service;

import com.yefei.ework.entity.request.RolePermissionAddRequest;
import com.yefei.ework.entity.result.CommonResult;

public interface RoleService {
    CommonResult<Boolean> add(String code);

    CommonResult<Boolean> addPermission(RolePermissionAddRequest request);
}
