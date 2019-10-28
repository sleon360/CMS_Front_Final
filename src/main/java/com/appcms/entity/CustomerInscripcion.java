package com.appcms.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CustomerInscripcion {

	@JsonProperty("card_id")
	String cardId;
	
	@JsonProperty("available_points")
	int availablePoints;
	
	String status;
	
	@JsonProperty("registration_date")
	String registrationDate;
	
	@JsonProperty("registration_time")
	String registrationTime;
	
	@JsonProperty("registered_points")
	int registeredPoints;
	
	@JsonProperty("category_name")
	String categoryName;
	
	@JsonProperty("unregister_date")
	String unregisterDate;

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public int getAvailablePoints() {
		return availablePoints;
	}

	public void setAvailablePoints(int availablePoints) {
		this.availablePoints = availablePoints;
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

	public int getRegisteredPoints() {
		return registeredPoints;
	}

	public void setRegisteredPoints(int registeredPoints) {
		this.registeredPoints = registeredPoints;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getUnregisterDate() {
		return unregisterDate;
	}

	public void setUnregisterDate(String unregisterDate) {
		this.unregisterDate = unregisterDate;
	}
	
}
