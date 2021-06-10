package com.kcl.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 项目名： vueadmin
 * 包名:    com.kcl.entity
 * 文件名   SysRoleMenu
 * 创建者
 * 创建时间: 2021/6/3 9:42 PM
 * 描述  ${TODO}
 */


@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysRoleMenu {

    private long id;
    private long roleId;
    private long menuId;
}

