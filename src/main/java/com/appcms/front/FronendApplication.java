package com.appcms.front;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.ErrorMvcAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {
	    "com.appcms.security",
	    "com.appcms.routes",
	    "com.appcms.front",
	    "com.appcms.error",
	    "com.appcms.router",
	    "com.appcms.model",
	    "com.appcms.services",
	    "com.cms.errors",
	    "com.cms.views"
	})

@SpringBootApplication
@EnableAutoConfiguration(exclude = {ErrorMvcAutoConfiguration.class})
public class FronendApplication {

	public static void main(String[] args) {
		SpringApplication.run(FronendApplication.class, args);
	}

}
