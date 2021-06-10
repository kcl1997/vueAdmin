package com.kcl.service;

import com.kcl.entity.SysRole;

import java.util.List;

/**
 * 项目名： vueadmin
 * 包名:    com.kcl.service
 * 文件名   SysRoleServiceImpl
 * 创建者
 * 创建时间: 2021/6/6 10:31 AM
 * 描述  ${TODO}
 */
public interface SysRoleService {

    //根据角色id，查找用户ids，用于redis删除
    List<Long> getUserIdsByRoleId(Long roleId);

    //根据用户id，查找角色 ids，用户处理 menuList
    List<Long> getRoleIdsByUserId(Long userId);

    // 查找所有 角色
    List<SysRole> getAllRoles();


    // 根据 id 删除 角色，用于 role manage 删除一条或批量删除数据
    void  deleteRoleById(Long id);


    // 保存一个角色
    void saveRole(SysRole role);

    // 更新一个角色
    void updateRole(SysRole role);


    // 根据 roleName 查找一个role
    SysRole getRoleByRoleName(String name);


}

