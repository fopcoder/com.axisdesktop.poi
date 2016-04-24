package com.axisdesktop.poi.entity;

public class PointFull {
	private long id;
	private double latitude;
	private double longitude;
	private String name;

	public PointFull() {
	}

	public PointFull( long id, double latitude, double longitude, String name ) {
		this.id = id;
		this.latitude = latitude;
		this.longitude = longitude;
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setId( long id ) {
		this.id = id;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude( double latitude ) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude( double longitude ) {
		this.longitude = longitude;
	}

	public String getName() {
		return name;
	}

	public void setName( String name ) {
		this.name = name;
	}

}
