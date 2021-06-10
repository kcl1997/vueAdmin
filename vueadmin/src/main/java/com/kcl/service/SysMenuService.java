package com.kcl.service;

import com.kcl.entity.SysMenu;

import java.util.List;

/**
 * 项目名： vueadmin
 * 包名:    com.kcl.service
 * 文件名   SysMenu
 * 创建者
 * 创建时间: 2021/6/5 11:57 PM
 * 描述  ${TODO}
 */
public interface SysMenuService {

    SysMenu getMenuById(Long id);


    // 根据菜单id查找 角色id， 删除redis记录用
    List<Long> getRoleIdsByMenuId(Long menuId);


    //根据 roleId,查找 menuIds， 用于返回 menuList
    List<Long> getMenuIdsByRoleId(Long roleId);


    // 获取所有的 menu，用于返回 menuManage menuTable
    List<SysMenu> getMenuTable();


    //根据 userId 查找 menuList，用户返回 menuList
    List<SysMenu> getMenuListByUserId(Long userId);

    //保存一个 menu，用于 menuTable 新增加
    void saveMenu(SysMenu menu);

    //更新一个 menu，用于 menuTable 修改
    void updateMenu(SysMenu menu);

    //根据 menuId ， 删除一个 menu
    void deleteMenuById(Long menuId);


}

