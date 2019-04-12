package com.appcms.security;

import java.io.IOException;
import java.util.Collections;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.appcms.entity.CredencialesEntity;

public class CustomerSecurityFilter extends AbstractAuthenticationProcessingFilter {

	
	
    public CustomerSecurityFilter(String url, AuthenticationManager authManager) {
        super(new AntPathRequestMatcher(url));
        setAuthenticationManager(authManager);
    }

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {
		// TODO Auto-generated method stub
		System.out.println("ERROR!!!!!!!!!!!!!!XXXXXXXXXXXX ");
		try
		{
		CredencialesEntity credenciales =new CredencialesEntity();
		credenciales.setTOKENONE(request.getSession().getAttribute("TOKENONE").toString());
		credenciales.setUserName(request.getParameter("username"));
		credenciales.setPassword(request.getParameter("password"));
		Authentication auth=getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(request.getSession().getAttribute("TOKENONE"),credenciales,Collections.emptyList()));
		 return auth;
		}catch(Exception ex)
		{
			RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
			redirectStrategy.sendRedirect(request, response, "/errores?errorToken");
			Authentication auth=SecurityContextHolder.getContext().getAuthentication();
			
			System.out.println("ERROR!!!!!!!!!!!!!! "+ex.getMessage());
			return auth;
		}
	}
	
	@Override
    protected void successfulAuthentication(
            HttpServletRequest req,
            HttpServletResponse res, FilterChain chain,
            Authentication auth) throws IOException, ServletException {
		super.setAuthenticationSuccessHandler(new CustomAuthenticationSuccessHandler());
		super.successfulAuthentication(req, res, chain, auth);

}
	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException failed)
			throws IOException, ServletException {
		SecurityContextHolder.clearContext();
		super.setAuthenticationFailureHandler(new CustomAuthenticationFailureHandler());
		super.unsuccessfulAuthentication(request, response, failed);
		
	}
	
	
	

}