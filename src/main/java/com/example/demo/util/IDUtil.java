package com.example.demo.util;

import java.util.UUID;

/**
 * 为id生成随机字符串
 */
public class IDUtil {
    public static String uuid(){
        return UUID.randomUUID().toString().replace("-","");
    }

}
