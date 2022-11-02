package com.ye.intercepter;

import org.jetbrains.annotations.NotNull;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * 拦截器
 */
public class LoginHandlerInterceptor implements HandlerInterceptor {

    // 检测全局session对象中是否有uid数据,如果有则放行,如果没有重定向到登录页面

    @Override
    public boolean preHandle(HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull Object handler) throws Exception {
        // 登录成功，uid 放入 session
        Object obj = request.getSession().getAttribute("uid");
        if (Objects.isNull(obj)){
            response.sendRedirect("/web/login.html");
            return false;
        }
        // 放行
        return true;
    }
}
