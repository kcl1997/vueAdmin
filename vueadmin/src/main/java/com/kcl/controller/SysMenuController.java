package com.kcl.controller;

import com.kcl.common.out.Result;
import com.kcl.entity.SysMenu;
import com.kcl.entity.SysUser;
import com.kcl.entity.dto.SysMenuDto;
import com.kcl.service.Impl.SysMenuServiceImpl;
import com.kcl.service.Impl.SysRoleServiceImpl;
import com.kcl.service.Impl.SysUserServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

/**
 * 项目名： vueadmin
 * 包名:    com.kcl.controller
 * 文件名   SysMenuController
 * 创建者
 * 创建时间: 2021/6/6 1:50 PM
 * 描述  ${TODO}
 */

@RestController
public class SysMenuController {

    @Autowired
    SysUserServiceImpl mSysUserService;
    @Autowired
    SysRoleServiceImpl mSysRoleService;
    @Autowired
    SysMenuServiceImpl mSysMenuService;


    // 获取 MenuList
    @GetMapping("/menuList")
    public Result getMenuList(Principal principal, HttpServletResponse response){
        String username = principal.getName();
        SysUser user = mSysUserService.getUserByUserName(username);


//        List<Long> menuIdList = new ArrayList<>();
//        // 根据 userId 查找角色Id
//        List<Long> roleIds = mSysRoleService.getRoleIdsByUserId(user.getId());
//        System.out.println("roleIds: " + roleIds);
//        // 根据 roleId 查询 menuList
//        for(int i = 0; i < roleIds.size(); i++){
//            List<Long> menuIds = mSysMenuService.getMenuIdsByRoleId(roleIds.get(i));
//            for(int j = 0; j < menuIds.size(); j++){
//                if(!menuIdList.contains(menuIds.get(j))) menuIdList.add(menuIds.get(j));
//            }
//        }

        List<SysMenu> sysMenuList = mSysMenuService.getMenuListByUserId(user.getId());

        List<SysMenuDto> menuDtos = new ArrayList<>();
        //menuList 转为 menuDtoList
        for(int i = 0; i < sysMenuList.size(); i++){
            SysMenu menu = sysMenuList.get(i);
            SysMenuDto sysMenuDto = new SysMenuDto();
            sysMenuDto.setId(menu.getId());
            sysMenuDto.setParentId(menu.getParentId());
            sysMenuDto.setTitle(menu.getName());
            sysMenuDto.setIcon(menu.getIcon());
            sysMenuDto.setLink(menu.getPath());
            sysMenuDto.setComponentURL(menu.getComponent());
//            if(menu.getType() == 1)
                sysMenuDto.setIndex(menu.getPerms());  // 随便写,不一样就行
            menuDtos.add(sysMenuDto);
        }

        //整理 menuDtos 成为树状结构
        List<SysMenuDto> menuList = new ArrayList<>();
        for(int i = 0; i < menuDtos.size(); i++){
            for(int j = 0; j < menuDtos.size(); j++){
                if(menuDtos.get(i).getId() == menuDtos.get(j).getParentId()){ //找儿子
                    List<SysMenuDto> children = menuDtos.get(i).getChildren();
                    if(children == null) children = new ArrayList<>();
                    children.add(menuDtos.get(j));
                    menuDtos.get(i).setChildren(children);
                }
            }
            if(menuDtos.get(i).getParentId() == 0) menuList.add(menuDtos.get(i));
        }

        return Result.success("请求成功",menuList);
    }


    //sys/menu/list'
    //获取 menu table
    @GetMapping("/sys/menu/list")
    public Result getMenuTable(){
        // 获取所有的 menu
        List<SysMenu> menuTable = mSysMenuService.getMenuTable();

        //整理 menuDtos 成为树状结构
        List<SysMenu> menuList = new ArrayList<>();
        for(int i = 0; i < menuTable.size(); i++){
            for(int j = 0; j < menuTable.size(); j++){
                if(menuTable.get(i).getId() == menuTable.get(j).getParentId()){ //找儿子
                    List<SysMenu> children = menuTable.get(i).getChildren();
                    if(children == null) children = new ArrayList<>();
                    children.add(menuTable.get(j));
                    menuTable.get(i).setChildren(children);
                }
            }
            if(menuTable.get(i).getParentId() == 0) menuList.add(menuTable.get(i));
        }

        return Result.success(menuList);
    }


    @PreAuthorize("hasRole('ROLE_admin')")
    @PostMapping("/sys/menu/save")
    public Result saveMenu(@RequestBody  SysMenu menu){
        System.out.println(menu);

        Timestamp ts = new Timestamp(System.currentTimeMillis());
        menu.setGmtCreated(ts);
        menu.setGmtUpdated(ts);
        mSysMenuService.saveMenu(menu);
        return Result.success(null);
    }

    @PreAuthorize("hasRole('ROLE_admin')")
    @PostMapping("/sys/menu/update")
    public Result updateMenu(@RequestBody  SysMenu menu){
        System.out.println(menu);

        Timestamp ts = new Timestamp(System.currentTimeMillis());
        menu.setGmtUpdated(ts);

        mSysMenuService.updateMenu(menu);
        return Result.success("更新成功！");
    }


    @PreAuthorize("hasRole('ROLE_admin')")
   //api/sys/menu/delete/
    @DeleteMapping("/sys/menu/delete/{menuId}")
    public Result deleteMenuById(@PathVariable("menuId")Long menuId){
        mSysMenuService.deleteMenuById(menuId);
        return Result.success("删除成功！");
    }





}

