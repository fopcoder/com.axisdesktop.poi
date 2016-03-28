package com.axisdesktop.crawler.parser;

import java.math.BigDecimal;

public class Location {
	private BigDecimal longitude;
	private BigDecimal latitude;

	public Location() {
	}

	public Location( double latitude, double longitude ) {
		this.longitude = BigDecimal.valueOf( longitude );
		this.latitude = BigDecimal.valueOf( latitude );
	}

	public BigDecimal getLongitude() {
		return longitude;
	}

	public void setLongitude( double longitude ) {
		this.longitude = BigDecimal.valueOf( longitude );
	}

	public BigDecimal getLatitude() {
		return latitude;
	}

	public void setLatitude( double latitude ) {
		this.latitude = BigDecimal.valueOf( latitude );
	}

	@Override
	public String toString() {
		return "Location [latitude=" + latitude + ", longitude=" + longitude + "]";
	}
}
