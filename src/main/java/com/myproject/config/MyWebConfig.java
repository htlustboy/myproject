package com.myproject.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.myproject.interceptor.BaseHandlerInterceptor;

/**
 * 拦截器相关的配置
 * @author ht
 *
 */
@Configuration
public class MyWebConfig extends WebMvcConfigurerAdapter{
	
	@Override
	public void addInterceptors(InterceptorRegistry registry){
		registry.addInterceptor(new BaseHandlerInterceptor());
	}
}
