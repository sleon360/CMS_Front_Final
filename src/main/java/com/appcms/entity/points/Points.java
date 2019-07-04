package com.appcms.entity.points;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Points {

	@JsonProperty("available_points")
	private int availablePoints;

	@JsonProperty("expiring_points")
	private ExpiringPoints expiringPoints;

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

}