package com.example.demo.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 公司组实体类
 */
@Data
public class SysGroup implements Serializable {

    private Integer id;

    private String name;

    private String code;

    private Integer parentId;

    private String remark;

    private Date creationTime;

}
