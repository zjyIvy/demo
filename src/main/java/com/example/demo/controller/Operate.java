package com.example.demo.controller;

import com.example.demo.utils.HttpResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("operate")
@Api(tags = "操作测试")
public class Operate {

    /**
     * 设置预警操作
     * @return 响应内容
     */
    @GetMapping("/setWarning")
    @PreAuthorize("hasAuthority('setWarning')")// 用于判断用户是否有指定权限
    @ResponseBody
    @ApiOperation(value="设置预警操作")
    public HttpResult setWarning(){
        return  HttpResult.success("成功");
    }

    /**
     * 查看平均值
     * @return 响应内容
     */
    @GetMapping("/selectMean")
    @PreAuthorize("hasAuthority('selectMean')")// 用于判断用户是否有指定权限
    @ResponseBody
    @ApiOperation(value="查看平均值")
    public HttpResult selectMean(){
        return  HttpResult.success("成功");
    }

    /**
     * 查看最大值
     * @return 响应内容
     */
    @GetMapping("/selectMax")
    @PreAuthorize("hasAuthority('selectMax')")// 用于判断用户是否有指定权限
    @ResponseBody
    @ApiOperation(value="查看最大值")
    public HttpResult selectMax(){
        return  HttpResult.success("成功");
    }

    /**
     * 查看最小值
     * @return 响应内容
     */
    @GetMapping("/selectMin")
    @PreAuthorize("hasAuthority('selectMin')")// 用于判断用户是否有指定权限
    @ResponseBody
    @ApiOperation(value="查看最小值")
    public HttpResult selectMin(){
        return  HttpResult.success("成功");
    }

    /**
     * 查看实时数据
     * @return 响应内容
     */
    @GetMapping("/selectReal")
    @PreAuthorize("hasAuthority('selectReal')")// 用于判断用户是否有指定权限
    @ResponseBody
    @ApiOperation(value="查看实时数据")
    public HttpResult selectReal(){
        return  HttpResult.success("成功");
    }

     /**
     * 导入
     * @return 响应内容
     */
    @GetMapping("/import")
    @PreAuthorize("hasAuthority('import')")// 用于判断用户是否有指定权限
    @ResponseBody
    @ApiOperation(value="导入")
    public HttpResult warningImport(){
        return  HttpResult.success("成功");
    }

     /**
     * 导出
     * @return 响应内容
     */
    @GetMapping("/export")
    @PreAuthorize("hasAuthority('export')")// 用于判断用户是否有指定权限
    @ResponseBody
    @ApiOperation(value="导出")
    public HttpResult warningExport(){
        return  HttpResult.success("成功");
    }

    /**
     * 添加设置
     * @return 响应内容
     */
    @GetMapping("/addEquipment")
    @PreAuthorize("hasAuthority('addEquipment') ")// 用于判断用户是否有指定权限
    @ResponseBody
    @ApiOperation(value="添加设置")
    public HttpResult addEquipment(){
        return  HttpResult.success("成功");
    }

    /**
     * 设置设备
     * @return 响应内容
     */
    @GetMapping("/setEquipment")
    @PreAuthorize("hasAuthority('setEquipment')")// 用于判断用户是否有指定权限
    @ResponseBody
    @ApiOperation(value="设置设备")
    public HttpResult setEquipment(){
        return  HttpResult.success("成功");
    }

    /**
     * 用户信息
     * @return 响应内容
     */
    @GetMapping("/selectUser")
    @PreAuthorize("hasAuthority('selectUser')")// 用于判断用户是否有指定权限
    @ResponseBody
    @ApiOperation(value="用户信息")
    public HttpResult selectUser(){
        return  HttpResult.success("成功");
    }

}
