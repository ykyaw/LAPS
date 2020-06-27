package com.team1.iss.trial.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.team1.iss.trial.component.LoginHandlerInterceptor;

//use WebMvcConfigurer to extend SpringMVC function
@Configuration
public class MyMvcConfig implements WebMvcConfigurer {

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		//when browser request / show the index.html page
		registry.addViewController("/main").setViewName("index");
		registry.addViewController("/").setViewName("login");
		registry.addViewController("/index.html").setViewName("login");
		registry.addViewController("/login").setViewName("login");
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
//		registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**")
//			.excludePathPatterns("/","/index.html","/login","/user/login");
	}
}
