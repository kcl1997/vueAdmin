package com.kcl.common.out;

import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.json.JSONUtil;

/**
 * 项目名： vueadmin
 * 包名:    com.kcl.common.out
 * 文件名   ResponseUtil
 * 创建者
 * 创建时间: 2021/6/3 11:06 PM
 * 描述  根据reponse写json数据到界面里
 */
public class ResponseUtil {

    public static void out(HttpServletResponse response, Result result) throws IOException {

//        response.setStatus(HttpServletResponse.SC_FORBIDDEN); //403 不能这样写，不然服务器就收不到了！！！
        //返回json格式
        response.setHeader("Content-Type","application/json;charset=utf-8");
//        PrintWriter writer = response.getWriter();
//        writer.write(JSONUtil.toJsonStr(result));
//        writer.flush();
//        writer.close();
       // response.setHeader("Access-Control-Allow-Origin", "*");

        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.write(JSONUtil.toJsonStr(result).getBytes("UTF-8"));
        outputStream.flush();
        outputStream.close();

    }
}

