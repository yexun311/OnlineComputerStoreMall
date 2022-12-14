package com.ye.config;

import com.ye.intercepter.LoginHandlerInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    /** 配置拦截器 */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 自定义拦截器对象
        HandlerInterceptor interceptor = new LoginHandlerInterceptor();
        // 配置白名单
        List<String> patterns = new ArrayList<>();
        patterns.add("/bootstrap3/**");
        patterns.add("/css/**");
        patterns.add("/images/**");
        patterns.add("/js/**");
        patterns.add("/web/register.html");
        patterns.add("/web/loginz.html");
        patterns.add("/web/index.html");
        patterns.add("/web/product.html");
        patterns.add("/reg");
        patterns.add("/login");
        patterns.add("/doc.html");
        patterns.add("/swagger-ui.html");

        //registry.addInterceptor(interceptor);完成拦截
        // 器的注册,后面的addPathPatterns表示拦截哪些url
        //这里的参数/**表示所有请求,再后面的excludePathPatterns表
        // 示有哪些是白名单,且参数是列表
        registry.addInterceptor(interceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(patterns);
        
    }

}
