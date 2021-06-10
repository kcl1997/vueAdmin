package com.kcl.service;

import com.kcl.entity.SysRole;
import com.kcl.entity.SysUser;

import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 项目名： vueadmin
 * 包名:    com.kcl.service
 * 文件名   SysUserService
 * 创建者
 * 创建时间: 2021/6/3 10:47 PM
 * 描述  ${TODO}
 */
public interface SysUserService {

    List<SysUser> getAllUser();

    SysUser getUserByUserName(String username);

//    List<SysMenu> getMenuListByUsername(String username);

    List<SysRole> getRolesByUserId(Long userId);

    List<Long> getMenuIdsByUserId(Long userId);

    // 用户角色重新分配
    // 角色权限重新分配
    // 修改菜单权限的编码
    // 都需要 删除对应的redis中的用户权限信息
    void clearUserRedisAuthorityByUserId(Long userId);
    void clearUserRedisAuthorityByRoleId(Long roleId);
    void clearUserRedisAuthorityByMenuId(Long menuId);



    // save 一个 user
    void saveUser(SysUser user);

    // update 一个 user
    void updateUser(SysUser user);

    // 根据 id 删除 user，用于 user manage 删除一条或批量删除数据
    void  deleteUserById(Long id);

    // ----分配角色-----
    // 根据 userid 查找所有 roleIds
    List<Long> getRoleIdsByUserId(Long userId);

}
