package com.appcms.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

//@EnableWebSecurity
/*@EnableGlobalMethodSecurity(
        securedEnabled = true,
        jsr250Enabled = true,
        prePostEnabled = true
)*/

@Configuration
@EnableWebSecurity(debug = false)
@Order(1)
@EnableGlobalAuthentication
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	
	@Autowired  
    private CustomAuthenticationProvider authProvider;
	
	@Override 
    protected void configure(HttpSecurity http) throws Exception {  

		  /*http.csrf().disable().authorizeRequests()
          .antMatchers("/auth","/error","/home","/login").permitAll()
          .anyRequest().authenticated()
          .and()
          .addFilterBefore(new LoginFilter("/auth", authenticationManager()),UsernamePasswordAuthenticationFilter.class)
          .addFilterBefore(new JwtFilter(),UsernamePasswordAuthenticationFilter.class).exceptionHandling();*/
//		http.csrf().disable();
//		,"/categoria/*/*/canje"
		http.authenticationProvider(authProvider).authorizeRequests()
	    .antMatchers("/user/**").access("hasRole('ROLE_ADMIN')")
//		.antMatchers("/user/**").access("hasRole('ROLE_USER')")
	    .antMatchers("/login","/auth","/home","/","/errores","/error","/logout").permitAll()
	    .and().addFilterBefore(new CustomerSecurityFilter("/auth", authenticationManager()),UsernamePasswordAuthenticationFilter.class)
	    .formLogin()
		.loginPage("/login")
		.loginProcessingUrl("/auth")
		.usernameParameter("username")
		.passwordParameter("password");
		
		
		 http.logout()
         .logoutUrl("/logout")
         .logoutSuccessUrl("/")
         .invalidateHttpSession( true )
         .deleteCookies("JSESSIONID").permitAll();
		 
	      http.sessionManagement()
          .sessionFixation()
          .newSession().and().exceptionHandling().accessDeniedPage("/error/403");
	      
	      

	      

		//http.sessionManagement().sessionFixation().newSession().and().rememberMe();//.tokenRepository(persistentTokenRepository())
		//.tokenValiditySeconds(1209600);

    }
	
	
	
	
     @Override  
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
          auth.authenticationProvider(authProvider);
          
    }

}