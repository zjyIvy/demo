package com.example.demo.controller;

import com.example.demo.entity.SysUser;
import com.example.demo.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("user")
@Api(tags = "用戶")
public class SysUserController {

    @Autowired
    private SysUserService userService;

    @GetMapping("/findUser")
    @ResponseBody
    @ApiOperation(value="查询当前登录用户")
    public SysUser findUser() {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();// 获取当前登录用户
        return userService.selectByName(name);
    }

}
