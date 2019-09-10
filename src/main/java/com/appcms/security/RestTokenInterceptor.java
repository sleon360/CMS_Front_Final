package com.appcms.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
public class RestTokenInterceptor extends HandlerInterceptorAdapter {

	String apiUrl;

	@Autowired
	public RestTokenInterceptor(@Qualifier("apiUrl") String apiUrl) {
		super();
		this.apiUrl = apiUrl;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
		RestAuthentication resAuth=new RestAuthentication();
		if(resAuth.RestAutenticationLayerOne(request, response, apiUrl).getBody()=="true")
		return true;
		else
			return false;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object object, ModelAndView model)
			throws Exception {
//	System.out.println("postROLES");

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object object, Exception arg3)
			throws Exception {
//	System.out.println("afterRoles");
	}
}