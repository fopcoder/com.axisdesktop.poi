package com.axisdesktop.poi.helper;

public class LocationInfo {
	private long id;
	private String name;
	private String description;
	private String shortDescription;
	private String address;
	private String link;
	private double latitude;
	private double longitude;

	public LocationInfo( long id, double latitude, double longitude, String name, String description,
			String shortDescription, String link, String address ) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.shortDescription = shortDescription;
		this.address = address;
		this.link = link;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public LocationInfo( long id, double latitude, double longitude, String name, String description ) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription( String shortDescription ) {
		this.shortDescription = shortDescription;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress( String address ) {
		this.address = address;
	}

	public String getLink() {
		return link;
	}

	public void setLink( String link ) {
		this.link = link;
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

	public long getId() {
		return id;
	}

	public void setId( long id ) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName( String name ) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription( String description ) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "LocationInfo [id=" + id + ", name=" + name + ", description=" + description + ", shortDescription="
				+ shortDescription + ", address=" + address + ", link=" + link + ", latitude=" + latitude
				+ ", longitude=" + longitude + "]";
	}

}
