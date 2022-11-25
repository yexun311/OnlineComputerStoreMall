package com.ye.config;

import com.google.common.base.Predicates; // accessible
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author yx
 * @since 2022-11-25
 */
@Configuration
@EnableSwagger2  //开启Swagger2
public class SwaggerConfig extends WebMvcConfigurationSupport {
    /**
     * 创建API应用
     * apiInfo() 增加API相关信息
     * 通过select()函数返回一个ApiSelectorBuilder实例,用来控制哪些接口暴露给Swagger来展现，
     * 指定扫描的包路径来定义指定要建立API的目录。
     */
    @Bean
    public Docket docket(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(adminApiInfo())
                .enable(true) //enable是否启动Swagger 如果为false，则swagger不能在浏览器中访问
                .groupName("adminApi")
                .select()
                //RequestHandlerSelectors 配置要扫描接口的方式
                //basePackage: 指定要扫描的包
                //any()：扫描全部
                //none()不扫描
                //withClassAnnotation: 扫描类上的注解，参数为一个注解的反射对象
                //withMethodeAnnotation: 扫描方法上的注解
                .apis(RequestHandlerSelectors.basePackage("com.example.swagger.controller"))
                //只显示admin下面的路径
                //.paths(Predicates.and(PathSelectors.regex("/admin/.*")))
                // 任意 url
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo adminApiInfo(){
        return new ApiInfoBuilder()
                .title("api文档")
                .description("系统接口描述")
                .version("1.0")
                //作者信息
                .contact(new Contact("yx","http://127.0.0.1:8081/doc.html","e-mail"))
                .build();
    }


    /**
     * 防止@EnableMvc把默认的静态资源路径覆盖了，手动设置的方式
     */
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 解决静态资源无法访问
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
        // 解决swagger无法访问
        registry.addResourceHandler("/swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        // 解决swagger的js文件无法访问
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
        // 配置knife4j 显示文档
        registry.addResourceHandler("/doc.html").addResourceLocations("classpath:/META-INF/resources/");
    }
}
