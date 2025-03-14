package com.example.bbs_0220.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/upload_img/**")  // 웹에서 접근할 경로
                .addResourceLocations("file:" + System.getProperty("user.dir") + "/upload_img/");
    }
}

