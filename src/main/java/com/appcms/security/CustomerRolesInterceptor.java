package com.appcms.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
public class CustomerRolesInterceptor extends HandlerInterceptorAdapter {

 @Override
 public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
	 return true;
 }

 @Override
 public void postHandle(HttpServletRequest request, HttpServletResponse response, 
		Object object, ModelAndView model)
		throws Exception {
//	System.out.println("postROLES");

 }

 @Override
 public void afterCompletion(HttpServletRequest request, HttpServletResponse response, 
		Object object, Exception arg3)
		throws Exception {
//	System.out.println("afterRoles");
 }
}