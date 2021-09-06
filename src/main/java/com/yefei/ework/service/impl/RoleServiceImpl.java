package com.yefei.ework.service.impl;

import com.yefei.ework.dao.PermissionDOMapper;
import com.yefei.ework.dao.RoleDOMapper;
import com.yefei.ework.dao.RolePermissionDOMapper;
import com.yefei.ework.entity.DO.PermissionDO;
import com.yefei.ework.entity.DO.RoleDO;
import com.yefei.ework.entity.DO.RolePermissionDO;
import com.yefei.ework.entity.request.RolePermissionAddRequest;
import com.yefei.ework.entity.result.CommonResult;
import com.yefei.ework.service.RoleService;
import com.yefei.ework.service.template.AbstractServiceTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RoleServiceImpl extends AbstractServiceTemplate implements RoleService {

    @Autowired
    private RoleDOMapper roleDOMapper;

    @Autowired
    private PermissionDOMapper permissionDOMapper;

    @Autowired
    private RolePermissionDOMapper rolePermissionDOMapper;

    @Override
    public CommonResult<Boolean> add(String code) {
        return execute(() -> {
            RoleDO roleDO = new RoleDO();
            String roleId = UUID.randomUUID().toString();
            roleDO.setId(roleId);
            roleDO.setCode(code);
            roleDOMapper.insert(roleDO);

            return true;
        });
    }

    @Override
    public CommonResult<Boolean> addPermission(RolePermissionAddRequest request) {
        return execute(() -> {
            for (String permissionCode : request.getPermissionCodes()) {
                RolePermissionDO rolePermissionDO = new RolePermissionDO();
                rolePermissionDO.setId(UUID.randomUUID().toString());

                RoleDO roleDO = roleDOMapper.selectByCode(request.getRoleCode());
                rolePermissionDO.setRoleId(roleDO.getId());
                PermissionDO permissionDO = permissionDOMapper.selectByCode(permissionCode);
                rolePermissionDO.setPermissionId(permissionDO.getId());

                rolePermissionDOMapper.insert(rolePermissionDO);
            }
            return true;
        });


    }
}
