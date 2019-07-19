package com.appcms.entity.points;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Points {

	@JsonProperty("available_points")
	private int availablePoints;

	@JsonProperty("expiring_points")
	private ExpiringPoints expiringPoints;
	
	@JsonProperty("registered_points")
	private ExpiringPoints registeredPoints;

	public int getAvailablePoints() {
		return availablePoints;
	}

	public void setAvailablePoints(int availablePoints) {
		this.availablePoints = availablePoints;
	}

	public ExpiringPoints getExpiringPoints() {
		return expiringPoints;
	}

	public void setExpiringPoints(ExpiringPoints expiringPoints) {
		this.expiringPoints = expiringPoints;
	}

	public ExpiringPoints getRegisteredPoints() {
		return registeredPoints;
	}

	public void setRegisteredPoints(ExpiringPoints registeredPoints) {
		this.registeredPoints = registeredPoints;
	}

}