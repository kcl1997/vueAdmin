package com.kcl.service;

/**
 * 项目名： vueadmin
 * 包名:    com.kcl.service
 * 文件名   SysRoleMenuService
 * 创建者
 * 创建时间: 2021/6/9 4:19 PM
 * 描述  ${TODO}
 */
public interface SysRoleMenuService {

    // delete menu by role 用于重新对 角色分配权限
    void deleteMenuByRoleId(Long roleId);

    void saveRoleMenuByRoleIdAndMenuId(Long roleId,Long menuId);
}

