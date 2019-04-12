package com.cms.errors;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.util.NestedServletException;


@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(value = {MaxUploadSizeExceededException.class})
	@ResponseBody
    public ApiError handleError1(MaxUploadSizeExceededException execption) {
		return new ApiError(500, execption.getMessage());
    }
	
	@ExceptionHandler(value = {NestedServletException.class})
	@ResponseBody
    public ApiError handleError2(NestedServletException execption) {
		return new ApiError(500, execption.getMessage());
    }
	
	@ExceptionHandler(value = {MultipartException.class})
	@ResponseBody
    public ApiError handleError3(MultipartException execption) {
		return new ApiError(500, execption.getMessage());
    }
	
    

}
