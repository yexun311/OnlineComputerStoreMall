package com.ye.utils;

import javax.servlet.http.HttpSession;

/**
 * Session 工具类
 */
public class SessionUtils {

    /** 获取 Session 对象中的 uid */
    @Deprecated
    public static Integer getUidFromSession(HttpSession session){
        return Integer.valueOf(session.getAttribute("uid").toString());
    }

    /** 获取 Session 对象中的 username */
    @Deprecated
    public static String getUsernameFromSession(HttpSession session){
        return session.getAttribute("username").toString();
    }


}
