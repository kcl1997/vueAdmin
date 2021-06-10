package com.kcl.entity;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 项目名： vueadmin
 * 包名:    com.kcl.entity
 * 文件名   SysUser
 * 创建者
 * 创建时间: 2021/6/3 9:41 PM
 * 描述  ${TODO}
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysUser {

    private long id;
    private String username;
    private String password;

    private String avatar;
    private String email;
    private String city;
    private Integer status;


    private Timestamp gmtLastLogin;
    private Timestamp gmtCreated;
    private Timestamp gmtUpdated;
}

