package com.example.demo.controller;

import com.example.demo.entity.SysGroup;
import com.example.demo.service.SysGroupService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("group")
@Api(tags = "公司")
public class SysGroupController {

    @Autowired
    private SysGroupService groupService;

    @PostMapping("/findGroup")
    @ResponseBody
    @ApiOperation(value="查询用户所属公司")
    public SysGroup findGroup(
            @ApiParam(required = true,name = "userId",value = "用户id") Integer userId
    ){
        return groupService.selectByUserId(userId);
    }

}
