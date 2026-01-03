package org.example.learn.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    // 在 WebConfig.java 中添加或确认以下配置
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 配置上传文件夹为静态资源
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("file:uploads/");
    }

}
