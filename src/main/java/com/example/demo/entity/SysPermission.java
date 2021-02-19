package com.example.demo.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 权限实体类
 */
@Data
public class SysPermission implements Serializable {

    private Integer id;

    private String name;

    private String code;

    private String remark;

    private Date creationTime;

}
