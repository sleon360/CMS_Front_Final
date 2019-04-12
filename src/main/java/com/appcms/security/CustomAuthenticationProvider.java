package com.appcms.security;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.appcms.entity.CredencialesEntity;


@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
	

	public CustomAuthenticationProvider() {  
        super();  
   }
	
	
    @Override
    public Authentication authenticate(Authentication authentication) 
      throws AuthenticationException {
    	
    	try {
    	//Map<String, Object> info = (Map<String, Object>)SecurityContextHolder.getContext().getAuthentication().getDetails();  
    	///String tok = (String) info.get("TOKENONE"); 
    	
    	System.out.println(authentication.getDetails()+" AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAABBBBBBBBBBBBBBBBBBBBBB");
    	
       /// String name = authentication.getName();
       // String password = authentication.getCredentials().toString();
        System.out.println(authentication.getName()+" - "+ authentication.getCredentials() +" TESTTTTTT!!! "+authentication.getName().equals("admin")+" - "+ authentication.getCredentials().equals("admin"));
        
        CredencialesEntity credenciales =(CredencialesEntity) authentication.getCredentials();
      //  System.out.println(CustomerAuthentication.CustomerAuth(authentication.getName(), authentication.getCredentials().toString(), ""));
        String TOKENTWO=CustomerAuthentication.CustomerAuth(credenciales.getUserName(),credenciales.getPassword(),credenciales.getTOKENONE()).replace("Bearer", "");
        
        System.out.println(TOKENTWO);
        if(TOKENTWO!=null) {
        	System.out.println("PASOLOGIN");
        	List<SimpleGrantedAuthority>  grantedAuths = new ArrayList<>();
            //grantedAuths.add(new SimpleGrantedAuthority("USER"));
            grantedAuths.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
            //return createSuccessAuthentication(authentication);
            
           // UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(authentication.getName(), authentication.getCredentials());
           // Authentication auth = new UsernamePasswordAuthenticationToken (authentication.getName(), authentication.getCredentials(), grantedAuths);
          //  auth.setAuthenticated(true);
          //  return auth;
           // return new UsernamePasswordAuthenticationToken(authentication.getName(), authentication.getCredentials(), grantedAuths);
            credenciales.setTOKENTWO(TOKENTWO);
            final Authentication auth = new UsernamePasswordAuthenticationToken(credenciales,credenciales.getPassword(),grantedAuths);
           
            
            // System.out.println("Authentication: " + SecurityContextHolder.getContext().getAuthentication());
           // SecurityContextHolder.getContext().setAuthentication(auth);
           // System.out.println("Authentication: " + SecurityContextHolder.getContext().getAuthentication());
            return auth;
            
            
        } else {
            return null;
        }
    	}catch(Exception ex)
    	{
    		return null;
    	}
        
    }
    
    @Override  
    public boolean supports(final Class authentication) {  
         return authentication.equals(UsernamePasswordAuthenticationToken.class);  
    }

}
