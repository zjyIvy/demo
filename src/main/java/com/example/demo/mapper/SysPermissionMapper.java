package com.example.demo.mapper;

import com.example.demo.entity.SysPermission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface SysPermissionMapper {

    List<SysPermission> selectPerm(@Param("roleIds") List<Integer> roleIds);

    List<SysPermission> selectUserPerm(@Param("userId") Integer userId);

    /**
     * 查询角色所拥有权限
     * @param userId 用户id
     * @return 返回权限集
     */
    List<Map<String,Object>> findPermByUserIdMap(@Param("userId") Integer userId);

    /**
     * 查询用户个人权限
     * @param userId 用户id
     * @return 返回权限集
     */
    List<Map<String,Object>> findUserPermByUserIdMap(@Param("userId") Integer userId);

}
