package com.kcl.dao;

import com.kcl.entity.SysRole;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * 项目名： vueadmin
 * 包名:    com.kcl.dao
 * 文件名   RoleDao
 * 创建者
 * 创建时间: 2021/6/3 10:44 PM
 * 描述  ${TODO}
 */

@Mapper
public interface SysRoleDao {


    //根据角色id，查找用户ids，用于redis删除
    @Select("select user_id from sys_user_role where sys_user_role.role_id = #{roleId}")
    List<Long> getUserIdsByRoleId(Long roleId);

    //根据用户id，查找角色 ids，用户处理 menuList
    @Select("select sr.role_id from sys_user_role sr where sr.user_id = #{userId}")
    List<Long> getRoleIdsByUserId(Long userId);

    // 查找所有 角色
    @Select("select * from sys_role")
    List<SysRole> getAllRoles();


    // 根据 id 删除 角色，用于 role manage 删除一条或批量删除数据
    @Delete("delete from sys_role where id = #{id}")
    void  deleteRoleById(Long id);

    // 保存一个角色
    @Insert("INSERT INTO sys_role ( NAME, CODE, remark, STATUS, gmt_created, gmt_updated ) VALUES (#{name},#{code},#{remark},#{status},#{gmtCreated},#{gmtUpdated})")
    void saveRole(SysRole role);


    // 更新一个角色
    @Update("UPDATE sys_role SET NAME = #{name},code = #{code},remark = #{remark}, status = #{status},gmt_created = #{gmtCreated},gmt_updated = #{gmtUpdated} where id = #{id}")
   void updateRole(SysRole role);

    // 根据 roleName 查找一个role
    @Select("select * from sys_role where name = #{name}")
    SysRole getRoleByRoleName(String name);

}

