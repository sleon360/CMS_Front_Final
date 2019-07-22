package com.appcms.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserCartolaMovimiento {
	
	String date;
	
	String description;
	
	String type;
	
	@JsonProperty("card_id")
	String cardId;
	
	int points;
	
	@JsonProperty("points_balance")
	int pointsBalance;
	
	public UserCartolaMovimiento(String date, String description, String type, int points, int pointsBalance) {
		this.date = date;
		this.description = description;
		this.type = type;
		this.points = points;
		this.pointsBalance = pointsBalance;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

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

	public int getPointsBalance() {
		return pointsBalance;
	}

	public void setPointsBalance(int pointsBalance) {
		this.pointsBalance = pointsBalance;
	}

}
