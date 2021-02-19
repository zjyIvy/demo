package com.example.demo.entity;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 用户实体类
 */
@Data
public class SysUser implements Serializable {

    private Integer id;

    private String loginName;

    private String loginPw;

    private String name;

    private String mobile;

    private String email;

    private String remark;

    private Timestamp creationTime;

}
