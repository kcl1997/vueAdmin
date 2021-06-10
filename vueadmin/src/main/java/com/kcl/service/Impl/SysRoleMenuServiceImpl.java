package com.kcl.service.Impl;

import com.kcl.dao.SysRoleMenuDao;
import com.kcl.service.SysRoleMenuService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 项目名： vueadmin
 * 包名:    com.kcl.service.Impl
 * 文件名   SysRoleMenuServiceImpl
 * 创建者
 * 创建时间: 2021/6/9 4:20 PM
 * 描述  ${TODO}
 */

@Service
@Transactional
public class SysRoleMenuServiceImpl implements SysRoleMenuService {


    @Autowired
    SysRoleMenuDao mSysRoleMenuDao;

    // delete menu by role 用于重新对 角色分配权限
    @Override
    public void deleteMenuByRoleId(Long roleId) {
        mSysRoleMenuDao.deleteMenuByRoleId(roleId);
    }

    @Override
    public void saveRoleMenuByRoleIdAndMenuId(Long roleId, Long menuId) {
        mSysRoleMenuDao.saveRoleMenuByRoleIdAndMenuId(roleId,menuId);
    }
}

