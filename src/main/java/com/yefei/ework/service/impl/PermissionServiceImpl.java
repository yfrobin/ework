package com.yefei.ework.service.impl;

import com.yefei.ework.dao.PermissionDOMapper;
import com.yefei.ework.entity.DO.PermissionDO;
import com.yefei.ework.entity.result.CommonResult;
import com.yefei.ework.service.PermissionService;
import com.yefei.ework.service.template.AbstractServiceTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PermissionServiceImpl extends AbstractServiceTemplate implements PermissionService {

    @Autowired
    private PermissionDOMapper permissionDOMapper;

    @Override
    public CommonResult<Boolean> add(String code) {
        return execute(() -> {
            PermissionDO permissionDO = new PermissionDO();
            permissionDO.setId(UUID.randomUUID().toString());
            permissionDO.setCode(code);
            permissionDOMapper.insert(permissionDO);

            return true;
        });
    }
}
