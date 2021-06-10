package com.kcl.entity.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 项目名： vueadmin
 * 包名:    com.kcl.entity.dto
 * 文件名   SysMenuDto
 * 创建者
 * 创建时间: 2021/6/6 1:57 PM
 *
 *
 *       title: '系统管理'           ,    //menu的title，路由的name
 *       index: 'systemMange',          //menu的index标号，没啥用
 *       icon: 'el-icon-s-operation',
 *       link: '',                       //没有用，路由地址
 *       componentURL: '',               //路由组件名
 *       children: [
 *
 *
 *      long      id;           2
 *      long      parentId;      1
 *      String    name;         用户管理
 *      String    path;          /sys/users
 *      String    perms;         sys:user:list
 *      String    component;     sys/User
 *      Integer   type;          1
 *      Integer   status;        1
 *      String    icon;         el-icon-s-custom
 *      Intege    oredrNum;       1
 *      Timestamp gmt_created;
 *      Timestamp gmt_updated;
 *
 *
 *
 */


@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysMenuDto {


    private Long id;
    private Long parentId;

    private String title;   // '系统管理', //menu的title，路由的name
    private String index;  //'systemMange', //menu的index标号，没啥用
    private String icon;   //'el-icon-s-operation',
    private String link;   //没有用，路由地址
    private String componentURL;  //路由组件名
    private List<SysMenuDto> children;
}

