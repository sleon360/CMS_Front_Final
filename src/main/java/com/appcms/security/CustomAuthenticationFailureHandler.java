package com.appcms.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {
    
	
	@Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException,ServletException 
    {
    	System.out.println("XXXXXXXXXXXXXXFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFCCCCCCCVVVVVVVVVVVVVVVV");
    	RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
    	redirectStrategy.sendRedirect(request, response, "/login?error");
    }
}