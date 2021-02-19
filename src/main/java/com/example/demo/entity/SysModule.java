package com.example.demo.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

/**
 * 菜单实体类
 */
@Data
public class SysModule implements Serializable {

    private Integer id;

    private String name;

    private String code;

    private Integer parentId;

    private String url;

    private BigInteger isShow;

    private String remark;

    private Date creationTime;

}
