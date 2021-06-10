package com.kcl.service.Impl;

import com.kcl.dao.SysUserRoleDao;
import com.kcl.service.SysUserRoleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 项目名： vueadmin
 * 包名:    com.kcl.service.Impl
 * 文件名   SysUserRoleService
 * 创建者
 * 创建时间: 2021/6/10 10:08 AM
 * 描述  ${TODO}
 */

@Service
@Transactional
public class SysUserRoleServiceImpl implements SysUserRoleService {


    @Autowired
    SysUserRoleDao mSysUserRoleDao;


    // delete menu by role 用于重新对 角色分配权限
    @Override
    public void deleteRoleByUserId(Long userId) {
        mSysUserRoleDao.deleteRoleByUserId(userId);
    }

    @Override
    public void saveUserRoleByUserIdAndUserId(Long userId, Long roleId) {
        mSysUserRoleDao.saveUserRoleByUserIdAndUserId(userId,roleId);
    }
}

