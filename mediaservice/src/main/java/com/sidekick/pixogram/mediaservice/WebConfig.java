package com.sidekick.pixogram.mediaservice;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                // by default refers to resources folder (customize it to fetch from /static under it)
                .addResourceLocations("classpath:/static/")
                // .addResourceLocations("classpath:/static/", "classpath:/static/profilepic","classpath:/static/media")
                .setCachePeriod(0);
    }
}
