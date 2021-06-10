package com.kcl.dao;

import com.kcl.entity.SysMenu;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * 项目名： vueadmin
 * 包名:    com.kcl.dao
 * 文件名   SysMenuDao
 * 创建者
 * 创建时间: 2021/6/3 10:44 PM
 * 描述  ${TODO}
 */

@Mapper
public interface SysMenuDao {
    @Select("select * from sys_menu where id = #{id}")
    SysMenu getMenuById(Long id);

    // 根据 menuId查找 角色id， 删除redis记录用
    @Select("select sys_role_menu.role_id from sys_role_menu where sys_role_menu.menu_id = #{menuId}")
    List<Long> getRoleIdsByMenuId(Long menuId);


    //根据 roleId,查找 menuIds， 用于返回 menuList
    @Select("select rm.menu_id from sys_role_menu rm where rm.role_id = #{roleId}")
    List<Long> getMenuIdsByRoleId(Long roleId);

    // 获取所有的 menu，用于返回 menuManage menuTable
    @Select("select * from sys_menu")
    List<SysMenu> getMenuTable();

    //根据 userId 查找 menuList，用户返回 menuList
    @Select("SELECT * FROM sys_menu WHERE sys_menu.id IN ( SELECT DISTINCT sm.menu_id FROM sys_user_role sr LEFT JOIN sys_role_menu sm ON sr.role_id = sm.role_id WHERE sr.user_id = #{userId} )")
    List<SysMenu> getMenuListByUserId(Long userId);


    //保存一个 menu，用于 menuTable 新增加
    @Insert("INSERT INTO sys_menu SET parent_id = #{parentId},name = #{name}, path = #{path}, perms = #{perms}, component = #{component}, type = #{type}, status = #{status},icon = #{icon}, order_num = #{orderNum},gmt_created = #{gmtCreated},gmt_updated = #{gmtUpdated}")
    void saveMenu(SysMenu menu);

    //更新一个 menu，用于 menuTable 修改
    @Update("update sys_menu SET parent_id = #{parentId},name = #{name}, path = #{path}, perms = #{perms}, component = #{component}, type = #{type}, status = #{status},icon = #{icon}, order_num = #{orderNum},gmt_created = #{gmtCreated},gmt_updated = #{gmtUpdated} where id = #{id}")
    void updateMenu(SysMenu menu);

    //根据 menuId ， 删除一个 menu
    @Delete("delete from sys_menu where id = #{menuId}")
    void deleteMenuById(Long menuId);



}

