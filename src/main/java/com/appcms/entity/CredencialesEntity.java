package com.appcms.entity;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

public class CredencialesEntity {
	
private String userName;
private String password;
private String TOKENONE;
private String TOKENTWO;


public CredencialesEntity()
{}

@Override
public String toString() {
	return "CredencialesEntity [userName=" + userName + ", password=" + password + ", TOKENONE=" + TOKENONE
			+ ", TOKENTWO=" + TOKENTWO + "]";
}

public String getTOKENONE() {
	return TOKENONE;
}

public void setTOKENONE(String tOKENONE) {
	TOKENONE = tOKENONE;
}

public String getTOKENTWO() {
	return TOKENTWO;
}

public void setTOKENTWO(String tOKENTWO) {
	TOKENTWO = tOKENTWO;
}



public String getUserName() {
	return userName;
}
public void setUserName(String userName) {
	this.userName = userName;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}





}
