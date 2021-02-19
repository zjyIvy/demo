package com.example.demo.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Api(tags = "登录操作")
public class LoginController {

    @GetMapping("/login")
    @ApiOperation(value="登录界面")
    public String login(){
        return "login";
    }

//    @RequestMapping("/index")
//    @ApiOperation(value="用户信息")
//    public String showHome() {
//        return "index";
//    }
}
