package com.kcl.service.Impl;

import com.kcl.common.redis.RedisUtil;
import com.kcl.dao.SysMenuDao;
import com.kcl.dao.SysUserDao;
import com.kcl.entity.SysMenu;
import com.kcl.entity.SysRole;
import com.kcl.entity.SysUser;
import com.kcl.service.SysRoleService;
import com.kcl.service.SysUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 项目名： vueadmin
 * 包名:    com.kcl.service.Impl
 * 文件名   SYsUserServiceImpl
 * 创建者
 * 创建时间: 2021/6/3 10:47 PM
 * 描述  ${TODO}
 */

@Service
@Transactional
public class SysUserServiceImpl implements SysUserService {


    @Autowired
    SysUserDao mSysUserDao;
    @Autowired
    SysMenuDao mSysMenuDao;
    @Autowired
    RedisUtil mRedisUtil;
    @Autowired
    SysRoleService mSysRoleService;
    @Autowired
    SysMenuServiceImpl mSysMenuService;


    @Override
    public List<SysUser> getAllUser(){
        return mSysUserDao.getAllUser();
    }

    @Override
    public SysUser getUserByUserName(String username){
        return mSysUserDao.getUserByUserName(username);
    }

    @Override
    public List<SysRole> getRolesByUserId(Long userId) {
        return mSysUserDao.getRolesByUserId(userId);
    }

    @Override
    public List<Long> getMenuIdsByUserId(Long userId) {
        return  mSysUserDao.getMenuIdsByUserId(userId);
    }



    //    @Override
//    public List<SysMenu> getMenuListByUsername(String username){
//        return mSysUserDao.getMenuListByUsername(username);
//    }


    //获取角色权限信息
    //"ROLE_admin,ROLE_normal,sys:user:list"
    public String getUserRoleAndAuthorityCodeByUserId(Long userId){
        String roleAndAuthorityCode = "";

        //获取角色
        List<SysRole> roleList = mSysUserDao.getRolesByUserId(userId);
        if(roleList.size() > 0){
            StringBuffer str = new StringBuffer();
            for(int i = 0; i < roleList.size(); i++){
                str.append("ROLE_" + roleList.get(i).getCode() + ",");
            }
            String roleCode = str.substring(0, str.length() - 1);
            roleAndAuthorityCode = roleCode;
        }

        //获取菜单权限
        List<Long> menuIds = mSysUserDao.getMenuIdsByUserId(userId);
        if(menuIds.size() > 0){
            StringBuffer str = new StringBuffer();
            if(roleList.size() > 0) str.append(",");
            for(int i = 0; i < menuIds.size(); i++){
                SysMenu menu = mSysMenuDao.getMenuById(menuIds.get(i));
                str.append(menu.getPerms() + ",");
            }
            String authCode = str.substring(0, str.length() - 1);
            roleAndAuthorityCode += authCode;
        }
        System.out.println("用户角色和权限信息：" + roleAndAuthorityCode);
        return roleAndAuthorityCode;
    }



    // 用户角色重新分配
    // 角色权限重新分配
    // 修改菜单权限的编码
    // 都需要 删除对应的redis中的用户权限信息

    @Override
    public void clearUserRedisAuthorityByUserId(Long userId) {
        mRedisUtil.del("authority"+ userId);
    }

    @Override
    public void clearUserRedisAuthorityByRoleId(Long roleId) {
        //根据角色id查找，用户id
        //select user_id from sys_user_role where sys_user_role.role_id = 3
        List<Long> userIds = mSysRoleService.getUserIdsByRoleId(roleId);
        for(int i = 0; i < userIds.size(); i++){
            clearUserRedisAuthorityByUserId(userIds.get(i));
        }
    }

    @Override
    public void clearUserRedisAuthorityByMenuId(Long menuId) {
        List<Long> roleIds = mSysMenuService.getRoleIdsByMenuId(menuId);
        for(int i = 0; i < roleIds.size(); i++){
            clearUserRedisAuthorityByRoleId(roleIds.get(i));
        }
    }

    // save 一个 user
    @Override
    public void saveUser(SysUser user) {
        mSysUserDao.saveUser(user);
    }

    // update 一个 user
    @Override
    public void updateUser(SysUser user) {
        mSysUserDao.updateUser(user);
    }


    // 根据 id 删除 user，用于 user manage 删除一条或批量删除数据
    @Override
    public void deleteUserById(Long id) {
        mSysUserDao.deleteUserById(id);
    }



    // ----分配角色-----
    // 根据 userid 查找所有 roleIds
    @Override
    public List<Long> getRoleIdsByUserId(Long userId) {
        return mSysUserDao.getRoleIdsByUserId(userId);
    }

}

