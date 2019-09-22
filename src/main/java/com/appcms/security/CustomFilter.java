package com.appcms.security;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class CustomFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		Enumeration<String> sessionAttributes = request.getSession().getAttributeNames();
		while(sessionAttributes.hasMoreElements()) {
			String parameter = sessionAttributes.nextElement();
		}
		
		Enumeration<String> parameters = request.getParameterNames();
		while(parameters.hasMoreElements()) {
			String parameter = parameters.nextElement();
		}		
		
		filterChain.doFilter(request, response);
		
	}
	

}
