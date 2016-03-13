package com.axisdesktop.crawler;

public class Category {
	private String name;
	private String uriPart;
	private String uri;
	private int externalId;
	private String parentUriPart;
	private int level;

	public Category( String name, String uriPart, String uri, int externalId, String parentUriPart, int level ) {
		this.name = name;
		this.uriPart = uriPart;
		this.uri = uri;
		this.externalId = externalId;
		this.parentUriPart = parentUriPart;
		this.level = level;
	}

	public String getName() {
		return name;
	}

	public void setName( String name ) {
		this.name = name;
	}

	public String getUri() {
		return uri;
	}

	public void setUri( String uri ) {
		this.uri = uri;
	}

	public int getExternalId() {
		return externalId;
	}

	public void setExternalId( int externalId ) {
		this.externalId = externalId;
	}

	public String getParentName() {
		return parentUriPart;
	}

	public void setParentName( String parentName ) {
		this.parentUriPart = parentName;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel( int level ) {
		this.level = level;
	}

	public String getUriPart() {
		return uriPart;
	}

	public void setUriPart( String uriPart ) {
		this.uriPart = uriPart;
	}

	public String getParentUriPart() {
		return parentUriPart;
	}

	public void setParentUriPart( String parentUriPart ) {
		this.parentUriPart = parentUriPart;
	}

	@Override
	public String toString() {
		return "Category [uriPart=" + uriPart + ", uri=" + uri + ", externalId=" + externalId + ", parentUriPart="
				+ parentUriPart + ", level=" + level + "]";
	}

	@Override
	public int hashCode() {
		return this.uri.hashCode();
	}

	@Override
	public boolean equals( Object obj ) {
		Category c = (Category)obj;
		return this.uri.equals( c.uri );
	}

}
