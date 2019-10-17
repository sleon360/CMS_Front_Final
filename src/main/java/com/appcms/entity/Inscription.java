package com.appcms.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Inscription {

	@JsonProperty("card_id")
	String cardId;
	
	int points;
	
	String status;
	
	@JsonProperty("registration_date")
	String registrationDate;
	
	@JsonProperty("registration_time")
	String registrationTime;
	
	@JsonProperty("unregister_date")
	String unregisterDate;
	
	@JsonProperty("commerce_name")
	String commerceName;

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(String registrationDate) {
		this.registrationDate = registrationDate;
	}

	public String getRegistrationTime() {
		return registrationTime;
	}

	public void setRegistrationTime(String registrationTime) {
		this.registrationTime = registrationTime;
	}

	public String getUnregisterDate() {
		return unregisterDate;
	}

	public void setUnregisterDate(String unregisterDate) {
		this.unregisterDate = unregisterDate;
	}

	public String getCommerceName() {
		return commerceName;
	}

	public void setCommerceName(String commerceName) {
		this.commerceName = commerceName;
	}
	
}
