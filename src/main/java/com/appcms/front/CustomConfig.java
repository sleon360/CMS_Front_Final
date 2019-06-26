package com.appcms.front;

import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.StringTemplateResolver;
import org.thymeleaf.dialect.IDialect;

@Configuration
@EnableWebMvc
public class CustomConfig implements WebMvcConfigurer {

	@Bean
	public ViewResolver viewResolver() {
		ThymeleafViewResolver resolver = new ThymeleafViewResolver();
		resolver.setTemplateEngine(templateEngine());
		// resolver.setApplicationContext(this.applicationContext);
		resolver.setCharacterEncoding("UTF-8");
		resolver.clearCache();
		resolver.setCache(false);
		return resolver;
	}

	@Bean
	public SpringTemplateEngine templateEngine() {
		SpringTemplateEngine templateEngine = new SpringTemplateEngine();
		templateEngine.setEnableSpringELCompiler(true);
		templateEngine.addTemplateResolver(new StringTemplateResolver());
		templateEngine.addDialect(new SpringSecurityDialect());
		templateEngine.addDialect(new LoadViewProcesor());
		return templateEngine;
	}

	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/WEB-INF/resources/**").addResourceLocations("/WEB-INF/resources/");

	}

	@Bean
	public IDialect springSecurityDialect() {
		SpringSecurityDialect dialect = new SpringSecurityDialect();
		return dialect;
	}
	
	@Bean(name = "apiUrl")
    public String getApiUrl() throws NamingException, SQLException {
		Context ctx = new InitialContext();
		String apiUrl = (String) ctx.lookup("apiUrl");
		return apiUrl;
    }

}
