package com.team1.iss.trial.config;

import com.team1.iss.trial.component.LoginHandlerInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyMvcConfig implements WebMvcConfigurer {


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
       registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**")
               .excludePathPatterns("/index.html","/","/login","/assets/**","/webjars/**","/userlogin");
    }
}
