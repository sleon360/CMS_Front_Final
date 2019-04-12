package com.appcms.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


@Component
public class CustomerInterceptor extends HandlerInterceptorAdapter {

 @Override
 public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
	
	//System.out.println("In preHandle we are Intercepting the Request"+object.getClass());
	//String requestURI = request.getRequestURI();
	//Integer personId = ServletRequestUtils.getIntParameter(request, "personId", 0);
	 String codigo=request.getHeader("AuthorizationCustomer");
	 if (codigo == null) {
		throw new ErrorControllerExection(403,"Error en AuthorizationCustomer");
	}
	 return true;
 }

 @Override
 public void postHandle(HttpServletRequest request, HttpServletResponse response, 
		Object object, ModelAndView model)
		throws Exception {
	System.out.println("In postHandle request processing "+ "completed by @RestController");

 }

 @Override
 public void afterCompletion(HttpServletRequest request, HttpServletResponse response, 
		Object object, Exception arg3)
		throws Exception {
	System.out.println("In afterCompletion Request Completed");
 }
}