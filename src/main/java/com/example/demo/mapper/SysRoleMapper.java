package com.example.demo.mapper;

import com.example.demo.entity.SysRole;
import com.example.demo.parameter.result.SysRoleResult;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 角色Mapper
 */
@Mapper
public interface  SysRoleMapper {

    /**
     * 根据主键id查询
     * @param id 角色id
     * @return 对应角色
     */
    SysRole selectById(@Param("id") Integer id);

    /**
     * 查询当前用户的角色
     * @param userId 用户id
     * @return 角色集
     */
    List<SysRole> selectByUserId(@Param("userId") Integer userId);

    /**
     * 查询当前用户的角色及权限
     * @param userName 用户名称
     * @return 角色及权限
     */
    List<SysRoleResult> selectRoles(@Param("userName") String userName);

    /**
     * 查询当前用户角色
     * @param userId 用户id
     * @return 用户角色集
     */
    List<Map<String,Object>> findRoleByUserIdMap(@Param("userId") Integer userId);
}
