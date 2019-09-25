package com.appcms.entity.points;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ExpiringPoints {

	@JsonProperty("points")
	private String points;
	
	@JsonProperty("expiration_date")
	private String expirationDate;

	public String getPoints() {
		return points;
	}

	public void setPoints(String points) {
		this.points = points;
	}

	public String getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}
	
}
