package com.kcl.dao;

import com.kcl.entity.SysRole;
import com.kcl.entity.SysUser;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 项目名： vueadmin
 * 包名:    com.kcl.dao
 * 文件名   UserDao
 * 创建者
 * 创建时间: 2021/6/3 10:43 PM
 * 描述  ${TODO}
 */

@Mapper
public interface SysUserDao {


    //测试
    @Select("select * from sys_user")
    List<SysUser> getAllUser();


    @Select("select * from sys_user where username = #{username}")
    SysUser getUserByUserName(String username);

//    //根据用户名查询菜单
//    List<SysMenu> getMenuListByUsername(String username);

    //根据用户id查询具有的角色
    List<SysRole> getRolesByUserId(Long userId);


    //根据用户id查询菜单的ids
    List<Long> getMenuIdsByUserId(Long userId);


    // save 一个 user
    @Insert("INSERT INTO sys_user SET username = #{username},password = #{password},avatar = #{avatar},email = #{email},city = #{city},status = #{status},gmt_last_login = #{gmtLastLogin},gmt_created = #{gmtCreated},gmt_updated = #{gmtUpdated}")
    void saveUser(SysUser user);

    // update 一个 user
    @Insert("update sys_user SET username = #{username},password = #{password},avatar = #{avatar},email = #{email},city = #{city},status = #{status},gmt_last_login = #{gmtLastLogin},gmt_created = #{gmtCreated},gmt_updated = #{gmtUpdated} where id = #{id}")
    void updateUser(SysUser user);


    // 根据 id 删除 user，用于 user manage 删除一条或批量删除数据
    @Delete("delete from sys_user where id = #{id}")
    void  deleteUserById(Long id);


    // ----分配角色-----
    // 根据 userid 查找所有 roleIds
    @Select("select role_id from  sys_user_role where user_id = #{userId}")
    List<Long> getRoleIdsByUserId(Long userId);



}

