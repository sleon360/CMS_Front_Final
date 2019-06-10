package com.appcms.model;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class ConfigJNDIModel {
	
	public static String getVar(String key)
	{
		String l_test = null;
		try {
			Context l_ctx = new InitialContext();
			l_test = (String) l_ctx.lookup("java:comp/env/"+key);
		} catch (NamingException e1) {
			e1.printStackTrace();
		}
		return l_test;
	}
}
