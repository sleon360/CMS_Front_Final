package com.appcms.entity.customer;

import com.appcms.entity.Scotiauser;

public class Customer {

	String jwt;
	Scotiauser scotiauser;
	
	public Customer(String jwt, Scotiauser scotiauser) {
		this.jwt = jwt;
		this.scotiauser = scotiauser;
	}

	public String getJwt() {
		return jwt;
	}
	
	public void setJwt(String jwt) {
		this.jwt = jwt;
	}
	
	public Scotiauser getScotiauser() {
		return scotiauser;
	}
	
	public void setScotiauser(Scotiauser scotiauser) {
		this.scotiauser = scotiauser;
	}
	
}
