package com.yxd.intercptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
@Configuration
public class CustomWebMvcConfigurerAdapter extends WebMvcConfigurerAdapter {
	
	@Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new SessionInterceptor()).addPathPatterns("/**");  //对来自/user/** 这个链接来的请求进行拦截
    }

}
