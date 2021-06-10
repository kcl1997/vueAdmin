package com.kcl.security.config;

import com.kcl.security.UserDetailServiceImpl;
import com.kcl.security.filter.CaptchaFilter;
import com.kcl.security.filter.JwtAuthenticationFilter;
import com.kcl.security.handler.JwtAuthenticationEntryPoint;
import com.kcl.security.handler.My403AccessDeniedHandler;
import com.kcl.security.handler.MyAuthenticationFailureHandler;
import com.kcl.security.handler.MyAuthenticationSuccessHandler;
import com.kcl.security.handler.MyLogoutSuccessHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * 项目名： vueadmin
 * 包名:    com.kcl.security.config
 * 文件名   SecurityConfig
 * 创建者
 * 创建时间: 2021/6/4 8:48 AM
 * 描述  SpringSecurity的全局配置类
 */

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true,prePostEnabled = true) //开启注解访问控制
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    //白名单
    private static final String[] URL_WHITELIST = {
            "/login",
            "/logout",
            "/captcha",
            "/favicon.ico"
    };

    @Autowired
    PasswordEncoder mPasswordEncoder; //加密实体类
    @Autowired
    MyAuthenticationSuccessHandler mMyAuthenticationSuccessHandler; //认证成功
    @Autowired
    MyAuthenticationFailureHandler mMyAuthenticationFailureHandler; //认证失败
    @Autowired
    CaptchaFilter mCaptchaFilter; //验证码过滤器[第一个]

    @Autowired
    My403AccessDeniedHandler mMy403AccessDeniedHandler; //403 已登录但是权限不足 sercurity独有的异常
    @Autowired
    JwtAuthenticationEntryPoint mJwtAuthenticationEntryPoint; // 未登录，权限不足，不能访问


    @Autowired
    MyLogoutSuccessHandler mMyLogoutSuccessHandler; //调用 /logout接口，手动清除权限信息和redis里面的内容



    @Bean //授权过滤器
    public JwtAuthenticationFilter mJwtAuthenticationFilter() throws Exception {
        return new JwtAuthenticationFilter(authenticationManager()); //父类 WebSecurityConfigurerAdapter的方法
    }



    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable(); //关掉csrf才可以表单接口和UserDetailService方法对接


        //登录配置
        http.formLogin()
                .usernameParameter("username") //提交表单的字段名
                .passwordParameter("password")
                //.loginPage("/login") //跳转至登录页，界面
                .loginProcessingUrl("/login") //表单处理接口，SpringSecurity来调用，不需要自己实现该接口
                //.successForwardUrl("/index")
                .successHandler(mMyAuthenticationSuccessHandler) //自定义登录成功处理器
                //.failureForwardUrl("/loginError");
                .failureHandler(mMyAuthenticationFailureHandler) //自定义登录失败处理器

         //禁用session
        .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)


        //配置拦截器
        .and()
                .authorizeRequests()
                .antMatchers(URL_WHITELIST).permitAll()
                .anyRequest().authenticated()



        //403权限不足，自定义处理
        .and().exceptionHandling()
                .accessDeniedHandler(mMy403AccessDeniedHandler) //未登录，用来解决匿名用户访问无权限资源时的异常
                .authenticationEntryPoint(mJwtAuthenticationEntryPoint)//用来解决认证过的用户访问无权限资源时的异常


         // //调用 /logout接口，手动清除权限信息和redis里面的内容
        .and()
                .logout()
                .logoutSuccessHandler(mMyLogoutSuccessHandler)


                //自定义拦截器
        .and()
                .addFilter(mJwtAuthenticationFilter())//除了login接口，其他接口都需要认证，需要和父类 WebSecurityConfigurerAdapter整合
            .addFilterBefore(mCaptchaFilter, UsernamePasswordAuthenticationFilter.class)//登录表单时时 验证码过滤器




        ;

    }



    //认证配置, 不配置也可以找到UserDeatailService
    @Autowired
    UserDetailServiceImpl mUserDetailService;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(mUserDetailService).passwordEncoder(mPasswordEncoder);
    }



}

