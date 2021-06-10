package com.kcl.security;

import com.kcl.common.redis.RedisUtil;
import com.kcl.entity.SysUser;
import com.kcl.service.Impl.SysUserServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 项目名： vueadmin
 * 包名:    com.kcl.security
 * 文件名   UserDetailServiceImpl
 * 创建者
 * 创建时间: 2021/6/4 9:06 AM
 * 描述  用户登录过程中
 * 描述  用户的认证和授权的实现(部分实现)
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {



    @Autowired
    PasswordEncoder mPasswordEncoder;
    @Autowired
    SysUserServiceImpl mSysUserService;
    @Autowired
    RedisUtil mRedisUtil;


    //自定义登录逻辑
    //security自带登录界面 会 和该方法进行对接
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //根据用户名查数据库，如果不存在就抛出UserNotFoundException
        SysUser user = mSysUserService.getUserByUserName(username);
        System.out.println("UserDetails");
        System.out.println(username);


        if(user == null) throw new UsernameNotFoundException("用户名不存在"); //该异常会进入AuthenticationFailureHandler！！！



        //[前后端分离，不需要在这里授权]
//        //授权
//        // 用户关联角色关联菜单 --> 权限
//        if("admin".equals(username)){
//            //数据库中已经进行加密的密码
//            String dbpassword = mPasswordEncoder.encode(user.getPassword());
//            // 用户名 + 密码 + 授权权限
//            List<GrantedAuthority> list = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_admin");
//            return new User(username,dbpassword, list); //用户输入的pass 和 dbpassword进行比较，security会自动校验是否成功
//        }


        String dbpassword = mPasswordEncoder.encode(user.getPassword()); //用户input的密码进行加密后，和dbpassword匹配

        //在这里授权没有太大的意义，前后端分离是无状态了
        //login只是这一个接口，如果这里授权，访问另外一个接口，还要再授权
        //每次访问给他授权就好了
        String roleAndAuthorityCode = mSysUserService.getUserRoleAndAuthorityCodeByUserId(user.getId());
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList(roleAndAuthorityCode);


        //权限信息保存至Redis
        mRedisUtil.set("authority"+user.getId(),roleAndAuthorityCode,3600*24*7); //默认7天，权限改动时删除该记录

        AccountUser accountUser = new AccountUser(user.getId(),user.getUsername(),dbpassword,grantedAuthorities);
        return accountUser;
    }




}

