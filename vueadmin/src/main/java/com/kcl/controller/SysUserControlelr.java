package com.kcl.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kcl.common.out.Result;
import com.kcl.entity.SysUser;
import com.kcl.service.Impl.SysUserRoleServiceImpl;
import com.kcl.service.Impl.SysUserServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.List;

/**
 * 项目名： vueadmin
 * 包名:    com.kcl.controller
 * 文件名   SysUserControlelr
 * 创建者
 * 创建时间: 2021/6/3 10:49 PM
 * 描述  ${TODO}
 */

@RestController
public class SysUserControlelr {

    @Autowired
    SysUserServiceImpl mSysUserService;

    @Autowired
    SysUserRoleServiceImpl mSysUserRoleService;


    @PreAuthorize("hasRole('ROLE_admin')")
    @GetMapping("/sys/user/all")
    public Result getAllUser(){
        return Result.success(mSysUserService.getAllUser());
    }


    @GetMapping("/sys/user/{username}")
    public Result getUserByUserName(@PathVariable("username")String username){
        return Result.success(mSysUserService.getUserByUserName(username));
    }

    // "/api/sys/user/list"
    //获取用户列表
    @GetMapping("/sys/user/list")
    public Result getUserList(String userName,Integer currentPage,Integer pageSize){

        System.out.println("userName: " + userName);
        System.out.println("currentPage: " + currentPage);
        System.out.println("pageSize: " + pageSize);

        PageInfo<SysUser> rolePageInfo = new PageInfo<>();
        if(userName == null){ //查找整个指定页的列表
            PageHelper.startPage(currentPage,pageSize);
            List<SysUser> allUsers = mSysUserService.getAllUser();
            rolePageInfo = new PageInfo<>(allUsers);
        }else{ //查找单个列表


        }
        return Result.success(rolePageInfo);
    }

    //'/sys/user/' + (this.editForm.id?'update' : 'save'), this.editForm)
    @PostMapping("/sys/user/save")
    public Result saveUser(@RequestBody SysUser user){
        System.out.println("----------saveUser-------------------");
        System.out.println(user);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        user.setPassword("888888");
        user.setGmtCreated(timestamp);
        user.setGmtUpdated(timestamp);
        mSysUserService.saveUser(user);
        return Result.success("保存成功！");
    }

    @PostMapping("/sys/user/update")
    public Result updateUser(@RequestBody SysUser user){
        user.setGmtUpdated(new Timestamp(System.currentTimeMillis()));
        mSysUserService.updateUser(user);
        return Result.success("更新成功！");
    }

    //"/api/sys/user/delete", ids)
    @DeleteMapping("/sys/user/delete/{ids}")
    public Result deleteUsersByUserIds(@PathVariable("ids")String ids){

        System.out.println("---------userIds ids-------");
        System.out.println(ids); //3,6,7,8,9
        String[] userIds = ids.split(",");

        for(int i = 0; i < userIds.length; i++){
            mSysUserService.deleteUserById(Long.parseLong(userIds[i]));
        }
        return Result.success("删除成功！");
    }



    // ------分配角色------
    //sys/user/roles/' + id).then
    @GetMapping("/sys/user/roles/{id}")
    public Result getRoleIdsByUserId(@PathVariable("id")Long userId){

        List<Long> roleIds = mSysUserService.getRoleIdsByUserId(userId);

        return Result.success(roleIds);
    }
    // submit 分配角色
    //'/api/sys/user/role/' + this.roleForm.id, roleIds).then(res => {
    @PostMapping("/sys/user/allocateRole/{userId}")
    public Result allocateRoleByUserId(@PathVariable("userId")Long userId,@RequestBody String roleIds){

        System.out.println("userId: " + userId);
        System.out.println("roleIds: " + roleIds);


        String[] roleIdStr = roleIds.split(",");

        // 1. sys_user_role  删除 user的关联的所有 role
        mSysUserRoleService.deleteRoleByUserId(userId);

        //2. 重新保存 所有 roleId 对应的  menuIds
        for(int i = 0; i < roleIdStr.length; i++){
            mSysUserRoleService.saveUserRoleByUserIdAndUserId(userId, Long.parseLong(roleIdStr[i]));
        }


        return Result.success(null);
    }

}

