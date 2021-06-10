package com.kcl.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kcl.common.out.Result;
import com.kcl.entity.SysRole;
import com.kcl.service.Impl.SysMenuServiceImpl;
import com.kcl.service.Impl.SysRoleMenuServiceImpl;
import com.kcl.service.Impl.SysRoleServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * 项目名： vueadmin
 * 包名:    com.kcl.controller
 * 文件名   SysRoleController
 * 创建者
 * 创建时间: 2021/6/8 5:41 PM
 * 描述  ${TODO}
 */

@RestController
public class SysRoleController {

    @Autowired
    SysRoleServiceImpl mSysRoleService;
    @Autowired
    SysMenuServiceImpl mSysMenuService;
    @Autowired
    SysRoleMenuServiceImpl mSysRoleMenuService;


    //获取 整个 roleList  -> user manage 分配 角色
    @GetMapping("/sys/role/all")
    public Result getAllRoles(){
        List<SysRole> allRoles = mSysRoleService.getAllRoles();
        return Result.success(allRoles);
    }



    //搜索指定角色名，或者 整个 roleList
    // currentPage [1,+oo]
    @GetMapping("/sys/role/list")
    public Result getSingleOrRoleList(String roleName,Integer currentPage,Integer pageSize){

        PageInfo<SysRole> rolePageInfo = new PageInfo<>();

        if("角色名".equals(roleName)){
            PageHelper.startPage(currentPage,pageSize);
            List<SysRole> allRoles = mSysRoleService.getAllRoles();
             rolePageInfo = new PageInfo<>(allRoles);

        }else{  //搜索指定角色
            PageHelper.startPage(currentPage,pageSize);
            SysRole role = mSysRoleService.getRoleByRoleName(roleName);
            List<SysRole> roleList = new ArrayList<>();
            roleList.add(role);
            rolePageInfo = new PageInfo<>(roleList);
        }

        return Result.success(rolePageInfo);
    }

    /// sys/role/delete/3,6,7
    @DeleteMapping("/sys/role/delete/{ids}")
    public Result deleteRolesByRoleIds(@PathVariable("ids")String ids){

        System.out.println("---------role ids-------");
        System.out.println(ids); //3,6,7,8,9
        String[] roleIds = ids.split(",");

        for(int i = 0; i < roleIds.length; i++){
            mSysRoleService.deleteRoleById(Long.parseLong(roleIds[i]));
        }

        return Result.success("删除成功！");
    }

    //'/api/sys/role/'+(this.editForm.id?'update':'save'
    @PostMapping("/sys/role/save")
    public Result saveRole(@RequestBody SysRole sysRole){
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        sysRole.setGmtCreated(timestamp);
        sysRole.setGmtUpdated(timestamp);

        mSysRoleService.saveRole(sysRole);
        return Result.success("保存成功");
    }

    //'/api/sys/role/'+(this.editForm.id?'update':'save'
    @PostMapping("/sys/role/update")
    public Result updateRole(@RequestBody SysRole sysRole){
        sysRole.setGmtUpdated(new Timestamp(System.currentTimeMillis()));
        mSysRoleService.updateRole(sysRole);
        return Result.success("更新成功！");
    }


    //获取 当前用户role  的  menuIds  --> 用于 分配角色时 显示原有的 menu
    // sys/role/info/' + id
    @GetMapping("/sys/role/menus/{id}")
    public Result getMenuIdsByRoleId(@PathVariable("id")Long roleId){
        List<Long> menuIds = mSysMenuService.getMenuIdsByRoleId(roleId);
        return Result.success(menuIds);
    }



     // 根据 roleId 分配 menuIds
    // '/sys/role/allocatePerms'+ this.permForm.id, checkedKeys
    @PostMapping("/sys/role/allocatePerms/{roleId}")
    public Result allocatePermByRoleId(@PathVariable("roleId")Long roleId,@RequestBody String checkedKeys){

        //checkedKeys     1,2,9,10,11,12,13,3,7,14,15,16,4,17,18,19,5,6,
        System.out.println("-------------checkedKeys----------------");
        System.out.println("roleId: "+ roleId);
        System.out.println(checkedKeys);




        String[] menuIds = checkedKeys.split(",");
        // 1. sys_role_menu  删除 role的关联的所有 menu
        mSysRoleMenuService.deleteMenuByRoleId(roleId);

        //2. 重新保存 所有 roleId 对应的  menuIds
        for(int i = 0; i < menuIds.length; i++){
            mSysRoleMenuService.saveRoleMenuByRoleIdAndMenuId(roleId, Long.parseLong(menuIds[i]));
        }

        return Result.success("保存成功");
    }



}

