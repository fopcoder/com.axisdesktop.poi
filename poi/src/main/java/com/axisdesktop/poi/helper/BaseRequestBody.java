package com.axisdesktop.poi.helper;

import java.util.ArrayList;
import java.util.List;

public class BaseRequestBody {
	private int start = 0;
	private int limit = 50;
	private List<Sorter> sorters = new ArrayList<>();
	private long tripId;
	private long pointId;
	private double latitude;
	private double longitude;
	private String name;
	private String description;

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

	public long getPointId() {
		return pointId;
	}

	public void setPointId( long pointId ) {
		this.pointId = pointId;
	}

	public long getTripId() {
		return tripId;
	}

	public void setTripId( long tripId ) {
		this.tripId = tripId;
	}

	public int getStart() {
		return start;
	}

	public void setStart( int start ) {
		this.start = start;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit( int limit ) {
		this.limit = limit;
	}

	public List<Sorter> getSorters() {
		return sorters;
	}

	public void setSorters( List<Sorter> sorters ) {
		this.sorters = sorters;
	}

	@Override
	public String toString() {
		return "BaseRequestBody [start=" + start + ", limit=" + limit + ", sorters=" + sorters + ", tripId=" + tripId
				+ ", pointId=" + pointId + ", latitude=" + latitude + ", longitude=" + longitude + ", name=" + name
				+ ", description=" + description + "]";
	}

}
