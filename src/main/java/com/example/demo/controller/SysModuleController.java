package com.example.demo.controller;

import com.example.demo.entity.SysModule;
import com.example.demo.service.SysModuleService;
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
@RequestMapping("module")
@Api(tags = "模块")
public class SysModuleController {

    @Autowired
    private SysModuleService moduleService;

    @PostMapping("/findModule")
    @ResponseBody
    @ApiOperation(value="查询角色所属模块")
    public List<SysModule> findModules(
            @ApiParam(required = true,name = "roleIds",value = "角色id集") List<Integer> roleIds
    ) {
        return moduleService.selectByRoleIds(roleIds);
    }

}
