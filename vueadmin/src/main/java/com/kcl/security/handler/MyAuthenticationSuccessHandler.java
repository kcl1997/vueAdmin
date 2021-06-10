package com.kcl.security.handler;

import com.kcl.common.out.ResponseUtil;
import com.kcl.common.out.Result;
import com.kcl.common.jwt.JwtUtils;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 项目名： vueadmin
 * 包名:    com.kcl.security.handler
 * 文件名   MyAuthenticationSuccessHandler
 * 创建者
 * 创建时间: 2021/6/4 8:57 AM
 * 描述  ${TODO}
 */

@Component
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {


    //前后端不分离项目url才有用
    private String url;

    public MyAuthenticationSuccessHandler() {

    }
    public MyAuthenticationSuccessHandler(String url) {
        this.url = url;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        //        httpServletResponse.sendRedirect(url);
        //        User user = (User) authentication.getPrincipal();
        //        System.out.println(user.getUsername());
        //        System.out.println(user.getPassword()); //null
        //        System.out.println(user.getAuthorities());

        //前后端分离模式 --> 返回jwt的三段token
        if(authentication != null){
            //之前的认证信息，删除掉
            //登录成功就在服务器端退出，清理资源
            new SecurityContextLogoutHandler().logout(httpServletRequest,httpServletResponse,authentication);
            System.out.println("MyAuthenticationSuccessHandler" + "  logout");
        }

        //生成jwt，并放置到请求头
//        Map<String,String> jwtmap = new HashMap<>();
//        jwtmap.put("username",authentication.getName());
//        String jwtToken = JwtUtils.createToken(jwtmap);
//        httpServletResponse.setHeader("jwt-token",jwtToken);

        Map<String,String> map = new HashMap<>();
        map.put("username",authentication.getName());
        System.out.println("登录成功： map.put username: " + authentication.getName());
        ResponseUtil.out(httpServletResponse, Result.success(JwtUtils.createToken(map)));

    }
}

