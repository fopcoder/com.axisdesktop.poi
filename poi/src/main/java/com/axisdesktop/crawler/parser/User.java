package com.axisdesktop.crawler.parser;

public class User {
	private String name;
	private String imageUri;
	private String externalId;

	public User() {
	}

	public User( String name, String extId ) {
		this.name = name;
		this.externalId = extId;
	}

	public String getName() {
		return name;
	}

	public void setName( String name ) {
		this.name = name;
	}

	public String getExternalId() {
		return externalId;
	}

	public void setExternalId( String extId ) {
		this.externalId = extId;
	}

	public String getImageUri() {
		return imageUri;
	}

	public void setImageUri( String image ) {
		this.imageUri = image;
	}

	@Override
	public String toString() {
		return "AppUserEntity [name=" + name + ", imageUri=" + imageUri + ", externalId=" + externalId + "]";
	}

}
