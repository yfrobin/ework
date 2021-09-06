package com.yefei.ework.core.security;

import com.yefei.ework.common.enums.ResultEnum;
import com.yefei.ework.entity.VO.UserVO;
import com.yefei.ework.entity.result.CommonResult;
import com.yefei.ework.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Component
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, SecurityException {
        CommonResult<UserVO> commonResult = userService.getByUsername(username);

        if (commonResult.getData() == null) {
            throw new SecurityException(ResultEnum.USER_NOT_EXIST.getCode(), ResultEnum.USER_NOT_EXIST.getDesc());
        }

        if (commonResult.isSuccess()) {
            UserVO userVO = commonResult.getData();

            if (userVO == null) {
                return null;
            }
            List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
            if (!CollectionUtils.isEmpty(userVO.getRoles())) {
                userVO.getRoles().forEach(roleVO -> {
                    SimpleGrantedAuthority roleSimpleGrantedAuthority = new SimpleGrantedAuthority("ROLE_" + roleVO.getCode());
                    grantedAuthorities.add(roleSimpleGrantedAuthority);

                });
            }

            return new User(userVO.getUsername(), userVO.getPassword(), grantedAuthorities);
        }
        return null;
    }
}
