package com.axisdesktop.poi.helper;

public class Sorter {
	private String property;
	private String direction;

	public String getProperty() {
		return property;
	}

	public void setProperty( String property ) {
		this.property = property;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection( String direction ) {
		this.direction = direction;
	}

	@Override
	public String toString() {
		return "Sorter [property=" + property + ", direction=" + direction + "]";
	}
}
