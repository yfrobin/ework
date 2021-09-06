package com.yefei.ework.controller;

import com.yefei.ework.entity.result.CommonResult;
import com.yefei.ework.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/permission")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @GetMapping("/add")
    public CommonResult<Boolean> add(String code) {
        return permissionService.add(code);
    }
}
