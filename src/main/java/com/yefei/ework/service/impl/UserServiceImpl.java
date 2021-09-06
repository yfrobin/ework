package com.yefei.ework.service.impl;

import com.yefei.ework.dao.RoleDOMapper;
import com.yefei.ework.dao.RolePermissionDOMapper;
import com.yefei.ework.dao.UserDOMapper;
import com.yefei.ework.dao.UserRoleDOMapper;
import com.yefei.ework.entity.DO.RoleDO;
import com.yefei.ework.entity.DO.UserDO;
import com.yefei.ework.entity.DO.UserRoleDO;
import com.yefei.ework.entity.VO.RoleVO;
import com.yefei.ework.entity.VO.UserVO;
import com.yefei.ework.entity.request.UserAddRequest;
import com.yefei.ework.entity.result.CommonResult;
import com.yefei.ework.service.UserService;
import com.yefei.ework.service.template.AbstractServiceTemplate;
import com.yefei.ework.service.template.CallBack;
import com.yefei.ework.utils.UserUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl extends AbstractServiceTemplate implements UserService {

    @Autowired
    private UserDOMapper userDOMapper;

    @Autowired
    private RoleDOMapper roleDOMapper;

    @Autowired
    private UserRoleDOMapper userRoleDOMapper;

    @Autowired
    private RolePermissionDOMapper rolePermissionDOMapper;

    @Override
    public CommonResult<Boolean> add(UserAddRequest request) {
        return execute(new CallBack<Boolean>() {
            @Override
            public Boolean call() {
                UserDO record = new UserDO();
                record.setId(UUID.randomUUID().toString());
                record.setUsername(request.getUsername());
                record.setPassword(new BCryptPasswordEncoder().encode(request.getPassword()));
                userDOMapper.insert(record);
                return true;
            }
        });
    }

    @Override
    public CommonResult<UserVO> getByUsername(String username) {
        return execute(new CallBack<UserVO>() {
            @Override
            public UserVO call() {
                UserDO userDO = userDOMapper.selectByUsername(username);
                if (userDO == null) {
                    return null;
                }

                UserVO userVO = new UserVO();
                BeanUtils.copyProperties(userDO, userVO);

                List<RoleDO> roleDOS = roleDOMapper.getByUserId(userDO.getId());

                List<RoleVO> roles = new ArrayList<>();
                for (RoleDO roleDO : roleDOS) {
                    RoleVO roleVO = new RoleVO();
                    BeanUtils.copyProperties(roleDO, roleVO);
                    roles.add(roleVO);
                }
                userVO.setRoles(roles);

                return userVO;
            }
        });
    }


    @Override
    public CommonResult<Boolean> addRole(String roleCode) {
        return execute(new CallBack<Boolean>() {
            @Override
            public Boolean call() {
                String currLoginUser = UserUtils.getCurrLoginUser();

                UserDO userDO = userDOMapper.selectByUsername(currLoginUser);
                if (userDO != null) {
                    RoleDO roleDO = roleDOMapper.selectByCode(roleCode);
                    if (roleDO != null) {
                        UserRoleDO userRoleDO = new UserRoleDO();
                        userRoleDO.setId(UUID.randomUUID().toString());
                        userRoleDO.setUserId(userDO.getId());
                        userRoleDO.setRoleId(roleDO.getId());

                        userRoleDOMapper.insert(userRoleDO);
                    }
                }
                return true;
            }
        });
    }
}
