package com.appcms.security;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;
import org.springframework.security.web.csrf.InvalidCsrfTokenException;
import org.springframework.security.web.csrf.MissingCsrfTokenException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class CustomAccessDeniedHandler extends AccessDeniedHandlerImpl {

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
		if (accessDeniedException instanceof MissingCsrfTokenException
				|| accessDeniedException instanceof InvalidCsrfTokenException) {
			if(request.getRequestURI().contains("logout")){
				request.getSession().invalidate();
		        response.sendRedirect(request.getContextPath()+"/");                                        
		    } else {
		    	response.setStatus(HttpStatus.UNAUTHORIZED.value());
				response.setCharacterEncoding("UTF-8");
				
				AuthenticationResponse authenticationResponse = new AuthenticationResponse();
				authenticationResponse.setStatus("FAIL");
				authenticationResponse.setMessage("Actualiza tú página e ingresa nuevamente");
				
				PrintWriter out = response.getWriter();
				out.print(new ObjectMapper().writeValueAsString(authenticationResponse));
				out.flush();
		    }			
		} else {
			super.handle(request, response, accessDeniedException);
		}
		
	}
}
