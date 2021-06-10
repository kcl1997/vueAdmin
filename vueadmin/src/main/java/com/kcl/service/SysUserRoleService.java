package com.kcl.service;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

/**
 * 项目名： vueadmin
 * 包名:    com.kcl.service
 * 文件名   SysUserRoleService
 * 创建者
 * 创建时间: 2021/6/10 10:08 AM
 * 描述  ${TODO}
 */
public interface SysUserRoleService {


    // delete menu by role 用于重新对 角色分配权限
    void deleteRoleByUserId(Long userId);



    void saveUserRoleByUserIdAndUserId(@Param("userId")Long userId, @Param("roleId") Long roleId);
}

