package com.axisdesktop.crawler.parser;

public class Location {
	private String longitude;
	private String latitude;

	public Location() {
	}

	public Location( String latitude, String longitude ) {
		this.longitude = longitude;
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude( String longitude ) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude( String latitude ) {
		this.latitude = latitude;
	}

	@Override
	public String toString() {
		return "Location [latitude=" + latitude + ", longitude=" + longitude + "]";
	}
}
