package com.kcl.common;

/**
 * 项目名： vueadmin
 * 包名:    com.kcl.common
 * 文件名   Test
 * 创建者
 * 创建时间: 2021/6/9 1:50 PM
 * 描述  ${TODO}
 */
public class Test {

    public static void main(String[] args) {
        String ids = "1,2,9,10,11,12,13,3,7,14,15,16,4,17,18,19,5,6,";
        String[] roleIds = ids.split(",");
        System.out.println(roleIds.length);
        for (String id : roleIds) {
            System.out.println(id);
        }
    }
}

