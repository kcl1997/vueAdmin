package com.kcl.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 项目名： vueadmin
 * 包名:    com.kcl.dao
 * 文件名   SysRoleMenuDao
 * 创建者
 * 创建时间: 2021/6/9 4:10 PM
 * 描述  ${TODO}
 */


@Mapper
public interface SysRoleMenuDao {




    // delete menu by role 用于重新对 角色分配权限
    @Delete("delete from sys_role_menu where role_id = #{roleId}")
    void deleteMenuByRoleId(Long roleId);



    @Insert("insert into sys_role_menu set role_id = #{roleId}, menu_id = #{menuId}")
    void saveRoleMenuByRoleIdAndMenuId(@Param("roleId")Long roleId, @Param("menuId") Long menuId);


}

