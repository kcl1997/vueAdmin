package com.kcl.security.filter;

import com.kcl.common.redis.RedisUtil;
import com.kcl.security.exception.CaptchaException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 项目名： vueadmin
 * 包名:    com.kcl.security.filter
 * 文件名   CaptchaFilter
 * 创建者
 * 创建时间: 2021/6/4 9:57 PM
 * 描述  OncePerRequestFilter 在UserName之前认证
 */
@Component
public class CaptchaFilter extends OncePerRequestFilter {

    /**
     *
     *
     *         loginForm: {
     *           username: 'admin',
     *           password: '123456',
     *           code: '',
     *           captchaKey: ''
     *         },
     *
     *
     */
    @Autowired
    RedisUtil mRedisUtil;
    @Autowired
    AuthenticationFailureHandler mAuthenticationFailureHandler;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

        String url = httpServletRequest.getRequestURI();
        if("/login".equals(url) && httpServletRequest.getMethod().equals("POST")){
            //校验 code + captchakey

            try {
                String code = httpServletRequest.getParameter("code");
                String captchaKey = httpServletRequest.getParameter("captchaKey");

                System.out.println(captchaKey + "--> " + code);
                if(code == null || captchaKey == null) throw new CaptchaException("验证码识别错误异常");

                boolean hHasKey = mRedisUtil.hHasKey(RedisUtil.HASH_KEY, captchaKey);
                if(!hHasKey) throw new CaptchaException("验证码已经过期！，请刷新验证码！");

                if(!code.equals(mRedisUtil.hget(RedisUtil.HASH_KEY,captchaKey))) throw new CaptchaException("验证码输入错误异常");


                //[不能删除，如果验证码正确，但是用户名和密码不正确就完蛋了]
                //验证成功
                //mRedisUtil.hdel(RedisUtil.HASH_KEY,captchaKey);
                //System.out.println("验证码验证成功，redis已删除");
            } catch (AuthenticationException e) {
                //终止登录逻辑
                mAuthenticationFailureHandler.onAuthenticationFailure(httpServletRequest,httpServletResponse,e);
            }

        }
        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }
}

