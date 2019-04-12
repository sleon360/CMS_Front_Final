package com.appcms.security;

import com.cms.errors.ApiError;

public class ErrorControllerExection extends Exception {
	  
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ApiError apmsg;
	public ErrorControllerExection(int code, String message) {
	    super();
	    apmsg = new ApiError(code, message);
	  }
	
	
	public ErrorControllerExection(int code, String message,String data) {
	    super();
	    apmsg = new ApiError(code, message);
	  }
	
	
	public ApiError getError()
	{
		return apmsg;
	}
	  
	  
}