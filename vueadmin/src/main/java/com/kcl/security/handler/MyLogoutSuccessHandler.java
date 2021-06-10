package com.kcl.security.handler;

import com.kcl.common.out.ResponseUtil;
import com.kcl.common.out.Result;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 项目名： vueadmin
 * 包名:    com.kcl.security.handler
 * 文件名   MyLogoutHandler
 * 创建者
 * 创建时间: 2021/6/6 11:21 AM
 * 描述  暂时不用
 */

@Component
public class MyLogoutSuccessHandler implements LogoutSuccessHandler {
    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {


        //手动清除权限
        if(authentication != null) new SecurityContextLogoutHandler().logout(httpServletRequest,httpServletResponse,authentication);
        //redis也要清除

        //删除前端的jwt-token



        Result result = Result.success("退出成功");
        ResponseUtil.out(httpServletResponse,result);
    }
}

