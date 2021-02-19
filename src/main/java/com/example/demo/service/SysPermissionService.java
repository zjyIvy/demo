package com.example.demo.service;

import com.example.demo.entity.SysPermission;
import com.example.demo.mapper.SysPermissionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("permService")
public class SysPermissionService {

    @Autowired
    private SysPermissionMapper permissionMapper;


    public List<SysPermission> selectPerm(List<Integer> roleIds) {
        return permissionMapper.selectPerm(roleIds);
    }

    public List<SysPermission> selectUserPerm(Integer userId) {
        return permissionMapper.selectUserPerm(userId);
    }

}
