package com.kcl.security.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * 项目名： vueadmin
 * 包名:    com.kcl.security.exception
 * 文件名   CaptchaException
 * 创建者
 * 创建时间: 2021/6/4 10:19 PM
 * 描述  验证码识别异常
 */
public class CaptchaException extends AuthenticationException {
    public CaptchaException(String msg) {
        super(msg);
    }
}

