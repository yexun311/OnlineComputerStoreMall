package com.ye.util;

import org.springframework.util.DigestUtils;

/**
 * MD5 加密工具类
 */
public class MD5Util {

    /** MD5加密 */
    public static String MD5(String str){
        if (StringUtil.isEmpty(str)){
            return str;
        }
        return DigestUtils.md5DigestAsHex(str.getBytes());
    }


}
