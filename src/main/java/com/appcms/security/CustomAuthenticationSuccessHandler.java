package com.appcms.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler  {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws
                                                                                                                                 IOException,
                                                                                                                                ServletException {
    	/*  User principal = (User) authentication.getPrincipal();
        System.out.println("principal" + principal.getUsername());
        boolean isAdmin = false;
        Iterator<GrantedAuthority> grantedAuthorityIterator = principal.getAuthorities().iterator();
        while (grantedAuthorityIterator.hasNext()) {
            if (grantedAuthorityIterator.next().getAuthority().equalsIgnoreCase("ROLE_ADMIN")) {
                isAdmin = true;
            }
        }
        if (isAdmin) {*/
    	System.out.println("GGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG");
    	RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
    	redirectStrategy.sendRedirect(request, response, "/admin");
    }
}