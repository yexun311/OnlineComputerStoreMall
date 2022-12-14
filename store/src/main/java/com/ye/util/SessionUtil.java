package com.ye.util;

import javax.servlet.http.HttpSession;

/**
 * Session 工具类
 */
public class SessionUtil {

    /** 获取 Session 对象中的 uid */
    public static Integer getUidFromSession(HttpSession session){
        return Integer.valueOf(session.getAttribute("uid").toString());
    }

    /** 获取 Session 对象中的 username */
    public static String getUsernameFromSession(HttpSession session){
        return session.getAttribute("username").toString();
    }


}
