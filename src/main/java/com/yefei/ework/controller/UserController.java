package com.yefei.ework.controller;

import com.yefei.ework.entity.VO.UserVO;
import com.yefei.ework.entity.request.UserAddRequest;
import com.yefei.ework.entity.result.CommonResult;
import com.yefei.ework.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 添加用户
     *
     * @param request
     * @return
     */
    @PostMapping("/add")
    public CommonResult<Boolean> add(@RequestBody UserAddRequest request) {
        return userService.add(request);
    }

    @GetMapping("/getByUsername")
    @PreAuthorize("hasRole('ADMIN')")
    public CommonResult<UserVO> getByUsername(String username) {
        return userService.getByUsername(username);
    }

    @GetMapping("/addRole")
    public CommonResult<Boolean> addRole(String roleCode) {
        return userService.addRole(roleCode);
    }

}
