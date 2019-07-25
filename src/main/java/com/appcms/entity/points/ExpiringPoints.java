package com.appcms.entity.points;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ExpiringPoints {

	@JsonProperty("points")
	private int points;
	
	@JsonProperty("expiration_date")
	private String expirationDate;

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public String getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}
	
}