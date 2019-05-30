package com.appcms.entity;

public class LoginUser {
	String rut;
	String pass;
	String token;
	
	
	public LoginUser() {
		super();
	}
	public LoginUser(String rut, String pass, String token) {
		super();
		this.rut = rut;
		this.pass = pass;
		this.token = token;
	}
	public String getRut() {
		return rut;
	}
	public void setRut(String rut) {
		this.rut = rut;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	@Override
	public String toString() {
		return "LoginUser [rut=" + rut + ", pass=" + pass + ", token=" + token + "]";
	}
	
	

}
