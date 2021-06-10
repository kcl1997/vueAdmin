package com.kcl.service.Impl;

import com.kcl.dao.SysRoleDao;
import com.kcl.entity.SysRole;
import com.kcl.service.SysRoleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 项目名： vueadmin
 * 包名:    com.kcl.service.Impl
 * 文件名   SysRoleServiceImpl
 * 创建者
 * 创建时间: 2021/6/6 10:32 AM
 * 描述  ${TODO}
 */

@Service
@Transactional
public class SysRoleServiceImpl implements SysRoleService {

    @Autowired
    SysRoleDao mSysRoleDao;

    //根据角色id，查找用户ids，用于redis删除
    public List<Long> getUserIdsByRoleId(Long roleId){
        return  mSysRoleDao.getUserIdsByRoleId(roleId);
    }

    //根据用户id，查找角色 ids，用户处理 menuList
    @Override
    public List<Long> getRoleIdsByUserId(Long userId) {
        return mSysRoleDao.getRoleIdsByUserId(userId);
    }


    // 查找所有 角色
    @Override
    public List<SysRole> getAllRoles() {
        return mSysRoleDao.getAllRoles();
    }

    // 根据 id 删除 角色，用于 role manage 删除一条或批量删除数据
    @Override
    public void deleteRoleById(Long id) {
        mSysRoleDao.deleteRoleById(id);
    }


    // 保存一个角色
    @Override
    public void saveRole(SysRole role) {
        mSysRoleDao.saveRole(role);
    }


    // 更新一个角色
    @Override
    public void updateRole(SysRole role) {
        mSysRoleDao.updateRole(role);
    }


    // 根据 roleName 查找一个role
    @Override
    public SysRole getRoleByRoleName(String name) {
        return mSysRoleDao.getRoleByRoleName(name);
    }
}

