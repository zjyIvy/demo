package com.example.demo.service;

import com.example.demo.entity.SysUser;
import com.example.demo.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysUserService {

    @Autowired
    private SysUserMapper userMapper;

    /**
     * 根据名字查询
     * @param loginName 名称
     * @return user类
     */
    public SysUser selectByName(String loginName){

        return userMapper.selectByName(loginName);
    }
}
