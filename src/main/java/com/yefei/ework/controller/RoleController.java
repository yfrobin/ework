package com.yefei.ework.controller;

import com.yefei.ework.entity.request.RolePermissionAddRequest;
import com.yefei.ework.entity.result.CommonResult;
import com.yefei.ework.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping("/add")
    public CommonResult<Boolean> add(String code) {
        return roleService.add(code);
    }

    @PostMapping("/addPermission")
    public CommonResult<Boolean> addPermission(@RequestBody RolePermissionAddRequest request) {
        return roleService.addPermission(request);
    }


}
