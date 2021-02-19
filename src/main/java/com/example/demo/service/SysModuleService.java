package com.example.demo.service;

import com.example.demo.entity.SysModule;
import com.example.demo.mapper.SysModuleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysModuleService {

    @Autowired
    private SysModuleMapper moduleMapper;

    public List<SysModule> selectByRoleIds(List<Integer> roleIds) {
        return moduleMapper.selectByRoleIds(roleIds);
    }
}
