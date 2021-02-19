package com.example.demo.controller;

import com.example.demo.entity.SysRole;
import com.example.demo.service.SysRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("role")
@Api(tags = "角色")
public class SysRoleController {

    @Autowired
    private SysRoleService roleService;

    @PostMapping("/findRole")
    @ResponseBody
    @ApiOperation(value="查询用户所属角色")
    public List<SysRole> findRole(
            @ApiParam(required = true,name = "userId",value = "用户id") Integer userId
    ) {
        return roleService.selectByUserId(userId);
    }

}
