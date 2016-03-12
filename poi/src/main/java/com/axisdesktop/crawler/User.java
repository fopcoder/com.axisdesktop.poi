package com.axisdesktop.crawler;

public class User {
	private String name;
	private String imageUri;
	private int extId;

	public User() {
	}

	public User( String name, int extId ) {
		this.name = name;
		this.extId = extId;
	}

	public String getName() {
		return name;
	}

	public void setName( String name ) {
		this.name = name;
	}

	public int getExtId() {
		return extId;
	}

	public void setExtId( int extId ) {
		this.extId = extId;
	}

	public String getImageUri() {
		return imageUri;
	}

	public void setImageUri( String image ) {
		this.imageUri = image;
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", imageUri=" + imageUri + ", extId=" + extId + "]";
	}

}
