package com.example.demo.mapper;

import com.example.demo.entity.SysGroup;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SysGroupMapper {

    /**
     * 查询当前用户所属公司
     *
     * @param userId 用户id
     * @return
     */
    SysGroup selectByUserId(@Param("userId") Integer userId);
}
