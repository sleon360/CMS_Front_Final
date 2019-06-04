package com.appcms.entity.points;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Points {

	@JsonProperty("available_points")
	private int availablePoints;

	@JsonProperty("expiring_points")
	private int expiringPoints;

	@JsonProperty("expiring_points_date")
	private String expiringPointsDate;

	public int getAvailablePoints() {
		return availablePoints;
	}

	public void setAvailablePoints(int availablePoints) {
		this.availablePoints = availablePoints;
	}

	public int getExpiringPoints() {
		return expiringPoints;
	}

	public void setExpiringPoints(int expiringPoints) {
		this.expiringPoints = expiringPoints;
	}

	public String getExpiringPointsDate() {
		return expiringPointsDate;
	}

	public void setExpiringPointsDate(String expiringPointsDate) {
		this.expiringPointsDate = expiringPointsDate;
	}
}