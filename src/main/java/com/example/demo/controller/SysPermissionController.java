package com.example.demo.controller;

import com.example.demo.entity.SysPermission;
import com.example.demo.service.SysPermissionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("perm")
@Api(tags = "权限")
public class SysPermissionController {

    @Autowired
    private SysPermissionService permissionService;

    @PostMapping("/findPerm")
    @ResponseBody
    @ApiOperation(value="查询角色所属权限")
    public List<SysPermission> findPerm(
            @ApiParam(required = true,name = "roleIds",value = "角色id集") List<Integer> roleIds
    ) {

        return permissionService.selectPerm(roleIds);
    }

    @PostMapping("/findUserPerm")
    @ResponseBody
    @ApiOperation(value="查询用户所属权限")
    public List<SysPermission> findUserPerm(
            @ApiParam(required = true,name = "userId",value = "用户id") Integer userId
    ) {

        return permissionService.selectUserPerm(userId);
    }

}
