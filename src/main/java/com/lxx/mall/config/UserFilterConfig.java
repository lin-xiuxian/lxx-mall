package com.lxx.mall.config;

import com.lxx.mall.filter.AdminFilter;
import com.lxx.mall.filter.UserFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 林修贤
 * @date 2023/2/12
 * @description User 过滤器配置
 */
@Configuration
public class UserFilterConfig {
    @Bean
    public UserFilter userFilter(){
        return new UserFilter();
    }

    @Bean(name = "userFilterConf")
    public FilterRegistrationBean userFilterConfig(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(userFilter());

        filterRegistrationBean.addUrlPatterns("/cart/*");
        filterRegistrationBean.addUrlPatterns("/order/*");
        filterRegistrationBean.addUrlPatterns("/user/update");
        filterRegistrationBean.setName("userFilterConf");
        return filterRegistrationBean;
    }
}
