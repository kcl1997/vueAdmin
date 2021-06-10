package com.kcl.security.base;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 项目名： vueadmin
 * 包名:    com.kcl.security.base
 * 文件名   MyBCryptPasswordEncoder
 * 创建者
 * 创建时间: 2021/6/4 8:51 AM
 * 描述  SpringSecurity必须的密码加密实例
 */


@Configuration
public class MyBCryptPasswordEncoder {

    @Bean
    public PasswordEncoder getPw(){
        return new BCryptPasswordEncoder();
    }

}

