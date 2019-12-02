package com.cms.errors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpStatusCodeException;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	private final static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	@ExceptionHandler(ViewRendererException.class)
	public ResponseEntity<String> handleViewException(ViewRendererException e) {
		logger.error(e.getMessage());
		return new ResponseEntity<String>("", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(HttpStatusCodeException.class)
	public String handleHttpException(HttpStatusCodeException e) {
		logger.error(e.getMessage());
		return "redirect:/error/" + e.getRawStatusCode();
	}
	
	@ExceptionHandler(Exception.class)
    public String handleException(Exception e) {
		logger.error(e.getMessage());
		return "redirect:/error/500";
    }

}