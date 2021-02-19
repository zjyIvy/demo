package com.example.demo.parameter.result;

import com.example.demo.entity.SysPermission;
import lombok.Data;

import java.util.List;

/**
 * 权限返回参数
 */
@Data
public class SysRoleResult {

    private Integer id;

    private String name;

    private String code;

    private List<SysPermission> permissions;

}
