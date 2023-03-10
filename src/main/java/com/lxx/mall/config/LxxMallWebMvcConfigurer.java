package com.lxx.mall.config;

import com.lxx.mall.common.Constant;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author 林修贤
 * @date 2023/2/12
 * @description
 */
@Configuration
public class LxxMallWebMvcConfigurer implements WebMvcConfigurer {
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/admin/**").addResourceLocations(
//                "classpath:/static/admin/");
//        registry.addResourceHandler("/images/**").addResourceLocations("file:" + Constant.FILE_UPLOAD_DIR);
//        registry.addResourceHandler("swagger-ui.html").addResourceLocations(
//                "classpath:/META-INF/resources/");
//        registry.addResourceHandler("/webjars/**").addResourceLocations(
//                "classpath:/META-INF/resources/webjars/");
//    }
@Override
public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/admin/**").addResourceLocations("classpath:/static/admin/");
    registry.addResourceHandler("/images/**")
            .addResourceLocations("file:" + Constant.FILE_UPLOAD_DIR);
    registry.addResourceHandler("swagger-ui.html").addResourceLocations(
            "classpath:/META-INF/resources/");
    registry.addResourceHandler("/webjars/**").addResourceLocations(
            "classpath:/META-INF/resources/webjars/");
}

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowCredentials(true)
                .allowedHeaders("*")
                .allowedMethods("GET", "POST", "DELETE", "PUT","PATCH")
                .maxAge(3600);
    }
}
