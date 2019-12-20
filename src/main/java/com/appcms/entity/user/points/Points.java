package com.appcms.entity.user.points;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Points {

	@JsonProperty("available_points")
	private String availablePoints;

	@JsonProperty("expiring_points")
	private ExpiringPoints expiringPoints;
	
	@JsonProperty("registered_points")
	private String registeredPoints;

	public String getAvailablePoints() {
		return availablePoints;
	}

	public void setAvailablePoints(String availablePoints) {
		this.availablePoints = availablePoints;
	}

	public ExpiringPoints getExpiringPoints() {
		return expiringPoints;
	}

	public void setExpiringPoints(ExpiringPoints expiringPoints) {
		this.expiringPoints = expiringPoints;
	}

	public String getRegisteredPoints() {
		return registeredPoints;
	}

	public void setRegisteredPoints(String registeredPoints) {
		this.registeredPoints = registeredPoints;
	}

}