package com.kcl.security.handler;

import com.kcl.common.out.ResponseUtil;
import com.kcl.common.out.Result;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 项目名： vueadmin
 * 包名:    com.kcl.security.handler
 * 文件名   MyAuthenticationFailureHandler
 * 创建者
 * 创建时间: 2021/6/4 8:58 AM
 * 描述  ${TODO}
 */

@Component
public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {


    //前后端不分离项目url才有用
    private String url;

    public MyAuthenticationFailureHandler() {
    }
    public MyAuthenticationFailureHandler(String url) {
        this.url = url;
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
//        httpServletResponse.sendRedirect(url);

        ResponseUtil.out(httpServletResponse, Result.error("认证失败! "+e.getMessage()));
    }
}

