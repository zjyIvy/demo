package com.example.demo.service;

import com.example.demo.entity.SysRole;
import com.example.demo.mapper.SysRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("roleService")
public class SysRoleService {

    @Autowired
    private SysRoleMapper roleMapper;

    /**
     * 根据主键id查询
     * @param id 角色id
     * @return 对应权限
     */
    public SysRole selectById(Integer id){
        return roleMapper.selectById(id);
    }

    /**
     * 查询当前用户的角色
     * @param userId 用户id
     * @return 角色集
     */
    public List<SysRole> selectByUserId(Integer userId){
        return roleMapper.selectByUserId(userId);
    }

}
