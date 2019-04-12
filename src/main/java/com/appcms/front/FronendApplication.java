package com.appcms.front;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;


@ComponentScan(basePackages = {
	    "com.appcms.security",
	    "com.appcms.routes",
	    "com.appcms.front",
	    "com.appcms.error",
	    "com.appcms.router"
	})

@SpringBootApplication
public class FronendApplication {

	public static void main(String[] args) {
		SpringApplication.run(FronendApplication.class, args);
	}

}
