package com.example.demo.mapper;

import com.example.demo.entity.SysModule;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysModuleMapper {

    List<SysModule> selectByRoleIds(@Param("roleIds") List<Integer> roleIds);
}
