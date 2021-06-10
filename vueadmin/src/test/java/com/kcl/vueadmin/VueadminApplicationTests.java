package com.kcl.vueadmin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kcl.dao.SysMenuDao;
import com.kcl.dao.SysRoleDao;
import com.kcl.entity.SysRole;
import com.kcl.service.Impl.SysMenuServiceImpl;
import com.kcl.service.Impl.SysRoleServiceImpl;
import com.kcl.service.Impl.SysUserServiceImpl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class VueadminApplicationTests {

    @Autowired
    SysUserServiceImpl mSysUserService;

    @Autowired
    SysMenuServiceImpl mSysMenuService;


    @Autowired
    SysRoleServiceImpl mSysRoleService;



    @Autowired
    SysMenuDao mSysMenuDao;

    @Autowired
    SysRoleDao mSysRoleDao;






    @Test
    void contextLoads() {

        PageHelper.startPage(1,5);
        List<SysRole> allRoles = mSysRoleService.getAllRoles();
        PageInfo<SysRole> rolePageInfo = new PageInfo<>(allRoles);

        System.out.println(rolePageInfo);

    }

}
