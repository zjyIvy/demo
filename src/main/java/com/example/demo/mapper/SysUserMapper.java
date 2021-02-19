package com.example.demo.mapper;

import com.example.demo.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SysUserMapper {

    /**
     * 根据名字查询
     * @param loginName 名称
     * @return user类
     */
    SysUser selectByName(@Param("loginName") String loginName);

}
