package com.cms.errors;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ApiError{

	int code;
    String message;
    String timestamp=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());

    public ApiError(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getStatus() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    
}