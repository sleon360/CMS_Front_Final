package com.appcms.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserCartolaMovimiento {
	
	@JsonProperty("card_id")
	String cardId;
	
	String date;
	
	String description;
	
	String id;
	
	@JsonProperty("available_points")
	String availablePoints;

	public UserCartolaMovimiento(String cardId, String date, String description, String id, String availablePoints) {
		this.cardId = cardId;
		this.date = date;
		this.description = description;
		this.id = id;
		this.availablePoints = availablePoints;
	}

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAvailablePoints() {
		return availablePoints;
	}

	public void setAvailablePoints(String availablePoints) {
		this.availablePoints = availablePoints;
	}

}
