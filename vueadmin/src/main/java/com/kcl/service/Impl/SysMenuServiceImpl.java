package com.kcl.service.Impl;

import com.kcl.dao.SysMenuDao;
import com.kcl.entity.SysMenu;
import com.kcl.service.SysMenuService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 项目名： vueadmin
 * 包名:    com.kcl.service.Impl
 * 文件名   SysMenuServiceImpl
 * 创建者
 * 创建时间: 2021/6/5 11:58 PM
 * 描述  ${TODO}
 */


@Service
@Transactional
public class SysMenuServiceImpl implements SysMenuService {

    @Autowired
    SysMenuDao mSysMenuDao;


    @Override
    public SysMenu getMenuById(Long id) {
        return mSysMenuDao.getMenuById(id);
    }


    // 根据菜单id查找 角色id， 删除redis记录用
    @Override
    public List<Long> getRoleIdsByMenuId(Long menuId) {
        return mSysMenuDao.getRoleIdsByMenuId(menuId);
    }



    //根据 roleId,查找 menuIds， 用于返回 menuList
    @Override
    public List<Long> getMenuIdsByRoleId(Long roleId) {
        return mSysMenuDao.getMenuIdsByRoleId(roleId);
    }


    // 获取所有的 menu，用于返回 menuManage menuTable
    @Override
    public List<SysMenu> getMenuTable() {
        return mSysMenuDao.getMenuTable();
    }


    //根据 userId 查找 menuList，用户返回 menuList
    @Override
    public List<SysMenu> getMenuListByUserId(Long userId) {
        return mSysMenuDao.getMenuListByUserId(userId);
    }

    //保存一个 menu，用于 menuTable 新增加
    @Override
    public void saveMenu(SysMenu menu) {
        mSysMenuDao.saveMenu(menu);
    }

    //更新一个 menu，用于 menuTable 修改
    @Override
    public void updateMenu(SysMenu menu) {
        mSysMenuDao.updateMenu(menu);
    }

    //根据 menuId ， 删除一个 menu
    @Override
    public void deleteMenuById(Long menuId) {
        mSysMenuDao.deleteMenuById(menuId);
    }

}

