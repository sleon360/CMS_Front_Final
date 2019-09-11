package com.appcms.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@Component

public class CustomAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		response.setCharacterEncoding("UTF-8");
		AuthenticationResponse authenticationResponse = new AuthenticationResponse();
		authenticationResponse.setStatus("OK");
		PrintWriter out = response.getWriter();
		out.print(new ObjectMapper().writeValueAsString(authenticationResponse));
		out.flush();
	}

}