package com.kcl.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 项目名： vueadmin
 * 包名:    com.kcl.dao
 * 文件名   SysUserRoleDao
 * 创建者
 * 创建时间: 2021/6/10 10:05 AM
 * 描述  ${TODO}
 */


@Mapper
public interface SysUserRoleDao {




    // delete menu by role 用于重新对 角色分配权限
    @Delete("delete from sys_user_role where user_id = #{userId}")
    void deleteRoleByUserId(Long userId);



    @Insert("insert into sys_user_role set user_id = #{userId}, role_id = #{roleId}")
    void saveUserRoleByUserIdAndUserId(@Param("userId")Long userId, @Param("roleId") Long roleId);

}

