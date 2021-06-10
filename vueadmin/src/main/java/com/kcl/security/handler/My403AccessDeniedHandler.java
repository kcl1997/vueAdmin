package com.kcl.security.handler;

/**
 * 项目名： vueadmin
 * 包名:    com.kcl.security.handler
 * 文件名   My403AccessDeniedHandler
 * 创建者
 * 创建时间: 2021/6/5 10:51 AM
 * 描述  ${TODO}
 */

import com.kcl.common.out.ResponseUtil;
import com.kcl.common.out.Result;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 项目名： learn_spring_security
 * 包名:    com.kcl.handler
 * 文件名   MyAccessDeniedHandler
 * 创建者
 * 创建时间: 2021/5/26 6:46 PM
 * 描述  自定义403处理, 登录之后权限不足
 * AuthenticationEntryPoint 用来解决匿名用户访问无权限资源时的异常
 * AccessDeineHandler       用来解决认证过的用户访问无权限资源时的异常
 */

@Component
public class My403AccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        httpServletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN); //403
        ResponseUtil.out(httpServletResponse, Result.error(403,"已经登录，但是权限不足，请联系管理员"));
    }
}
