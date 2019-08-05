package com.appcms.security;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler  {
	protected Log logger = LogFactory.getLog(this.getClass());
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	
	/*@Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws
                                                                                                                                 IOException,
                                                                                                                                ServletException {
    	 User principal = (User) authentication.getPrincipal();
        System.out.println("principal" + principal.getUsername());
        boolean isAdmin = false;
        Iterator<GrantedAuthority> grantedAuthorityIterator = principal.getAuthorities().iterator();
        while (grantedAuthorityIterator.hasNext()) {
            if (grantedAuthorityIterator.next().getAuthority().equalsIgnoreCase("ROLE_ADMIN")) {
                isAdmin = true;
            }
        }
        if (isAdmin) {
    	String referer = request.getHeader("Referer");
    	System.out.println("GGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG - "+referer);
       	RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
       	if(response.isCommitted() == false) {
       		redirectStrategy.sendRedirect(request, response, referer);
       	} else {
       		
       	}
    }
*/
        	 
        	 
        	 @Override
        	    public void onAuthenticationSuccess(HttpServletRequest request, 
        	      HttpServletResponse response, Authentication authentication)
        	      throws IOException {
        	  
        	        handle(request, response, authentication);
        	        clearAuthenticationAttributes(request);
        	    }
        	 
        	    protected void handle(HttpServletRequest request, 
        	      HttpServletResponse response, Authentication authentication)
        	      throws IOException {
        	    	String referer = request.getHeader("Referer");
        	        String targetUrl = referer;
        	 
        	        if (response.isCommitted()) {
        	            logger.debug(
        	              "Response has already been committed. Unable to redirect to "
        	              + targetUrl);
        	            return;
        	        }
        	 
        	        redirectStrategy.sendRedirect(request, response, targetUrl);
        	    }
        	    
        	    
        	    protected void clearAuthenticationAttributes(HttpServletRequest request) {
        	        HttpSession session = request.getSession(false);
        	        if (session == null) {
        	            return;
        	        }
        	        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
        	    }
        	 
        	    public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
        	        this.redirectStrategy = redirectStrategy;
        	    }
        	    protected RedirectStrategy getRedirectStrategy() {
        	        return redirectStrategy;
        	    }


}