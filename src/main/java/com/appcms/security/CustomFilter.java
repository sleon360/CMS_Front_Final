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
		System.out.println("getName() de clase: " + HttpServletResponse.class.getName());
		Enumeration<String> sessionAttributes = request.getSession().getAttributeNames();
		while(sessionAttributes.hasMoreElements()) {
			String parameter = sessionAttributes.nextElement();
			System.out.println("Parámetro de sesión " + parameter + ": " + request.getParameter(parameter));
		}
		
		Enumeration<String> parameters = request.getParameterNames();
		while(parameters.hasMoreElements()) {
			String parameter = parameters.nextElement();
			System.out.println("Parámetro enviado " + parameter + ": " + request.getParameter(parameter));
		}
		
		
		/*String actualToken = request.getHeader(csrfToken.getHeaderName());
		if (actualToken == null) {
			actualToken = request.getParameter(csrfToken.getParameterName());
			System.out.println("CSRF obtenido " + actualToken);
			System.out.println("CSRF header " + csrfToken.getHeaderName());
			System.out.println("CSRF parameter " + csrfToken.getParameterName());
		}*/
		
		
		
		filterChain.doFilter(request, response);
		
	}
	

}
