package com.appcms.entity;

public class CustomerRewardResponse {

	String status;
	String mensaje;
	CustomerReward customerReward;
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getMensaje() {
		return mensaje;
	}
	
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	
	public CustomerReward getCustomerReward() {
		return customerReward;
	}
	
	public void setCustomerReward(CustomerReward customerReward) {
		this.customerReward = customerReward;
	}

}
