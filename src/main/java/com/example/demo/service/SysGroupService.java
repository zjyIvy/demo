package com.example.demo.service;

import com.example.demo.entity.SysGroup;
import com.example.demo.mapper.SysGroupMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysGroupService {

    @Autowired
    private SysGroupMapper groupMapper;

    public SysGroup selectByUserId(Integer userId) {
        return groupMapper.selectByUserId(userId);
    }
}
