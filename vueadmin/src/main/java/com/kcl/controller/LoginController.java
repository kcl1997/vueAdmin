package com.kcl.controller;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.kcl.common.out.Result;
import com.kcl.common.redis.RedisUtil;
import com.kcl.entity.SysUser;
import com.kcl.service.Impl.SysUserServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.Principal;
import java.util.Base64;
import java.util.Map;

import javax.imageio.ImageIO;

import cn.hutool.core.lang.UUID;
import cn.hutool.core.map.MapBuilder;

/**
 * 项目名： vueadmin
 * 包名:    com.kcl.controller
 * 文件名   AuthController
 * 创建者
 * 创建时间: 2021/6/4 5:50 PM
 * 描述  认证
 */

@CrossOrigin
@RestController
public class LoginController {


    @Autowired
    DefaultKaptcha mDefaultKaptcha;
    @Autowired
    RedisUtil mRedisUtil;
    @Autowired
    SysUserServiceImpl mSysUserService;

    @GetMapping("/captcha")
    public Result captcha() throws IOException {
        String key = UUID.randomUUID().toString();
        String code = mDefaultKaptcha.createText();

        BufferedImage image = mDefaultKaptcha.createImage(code);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(image,"jpg",byteArrayOutputStream);


        String base64Img = "data:image/jpeg;base64," + Base64.getEncoder().encodeToString(byteArrayOutputStream.toByteArray());
        mRedisUtil.hset(RedisUtil.HASH_KEY,key,code,30); //2分钟

        Map<Object, Object> map = MapBuilder.create().put("key", key).put("image", base64Img).build();
        return Result.success(map);
    }

    //当前登录用户的信息
    @GetMapping("/sys/userInfo")
    public Result getNowLoginUserInfo(Principal principal){
        String username = principal.getName();
        SysUser user = mSysUserService.getUserByUserName(username);
        return Result.success(user);
    }


}

