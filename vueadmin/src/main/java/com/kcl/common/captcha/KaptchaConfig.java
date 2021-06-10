package com.kcl.common.captcha;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * 项目名： vueadmin
 * 包名:    com.kcl.common.captcha
 * 文件名   KaptchaConfig
 * 创建者
 * 创建时间: 2021/6/4 5:40 PM
 * 描述  ${TODO}
 */

@Configuration
public class KaptchaConfig  {

    @Bean
    public DefaultKaptcha producer(){
        Properties properties = new Properties();
        properties.put("kaptcha.border","no");
        properties.put("kaptcha.textproducer.font.color","black");
        properties.put("kaptcha.textproducer.char.space","4");
        properties.put("kaptcha.textproducer.font.size","30");

        properties.put("kaptcha.textproducer.char.string","0123456789");
        properties.put("kaptcha.textproducer.char.length","4");


        properties.put("kaptcha.noise.color","red");



        properties.put("kaptcha.image.height","40");
        properties.put("kaptcha.image.width","100");


        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        defaultKaptcha.setConfig(new Config(properties));
        return defaultKaptcha;
    }
}

