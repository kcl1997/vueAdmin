package com.kcl.entity;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 项目名： vueadmin
 * 包名:    com.kcl.entity
 * 文件名   SysRole
 * 创建者
 * 创建时间: 2021/6/3 9:42 PM
 * 描述  ${TODO}
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysRole {

    private long id;
    private String name; //管理员
    private String code; //角色编码 如 admin
    private String remark; //角色描述
    private Integer status;


    private Timestamp gmtCreated;
    private Timestamp gmtUpdated;

}

