package com.ye.utils;

/**
 * 字符串工具类
 */
public class StringUtils {

    /**
     * 判断是否为空（空 或 null 均返回 true）
     */
    public static boolean isEmpty(String str){
        return str == null || str.equals("");
    }

    public static boolean isNotEmpty(String str){
        return !isEmpty(str);
    }
}
