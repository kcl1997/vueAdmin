package com.kcl.entity;

import java.sql.Timestamp;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 项目名： vueadmin
 * 包名:    com.kcl.entity
 * 文件名   SysMenu
 * 创建者
 * 创建时间: 2021/6/3 9:42 PM
 * 描述  ${TODO}
 */


@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysMenu {

    private Long id;
    private Long parentId;

    private String name;
    private String path;
    private String perms;
    private String component;
    private Integer type;
    private Integer status;
    private String icon;
    private Integer orderNum;

    private Timestamp gmtCreated;
    private Timestamp gmtUpdated;


    //sysMenuTable用
    private List<SysMenu> children;

}

