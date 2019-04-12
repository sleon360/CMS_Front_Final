package com.appcms.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class ConfigCustomer  extends WebMvcConfigurationSupport  {

	@Autowired
	CustomerInterceptor CustomerInterceptor;
	
	@Autowired
	CustomerRolesInterceptor RolesInterceptor;
	
	@Autowired
	RestTokenInterceptor TokenInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		//registry.addInterceptor(CustomerInterceptor).addPathPatterns("/v1/customer/**");
		//registry.addInterceptor(RolesInterceptor).addPathPatterns("/v1/customer/**");
		registry.addInterceptor(TokenInterceptor).addPathPatterns("/**").excludePathPatterns("/errores");
	}
}
