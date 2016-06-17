package com.axisdesktop.poi.helper;

import javax.validation.constraints.Min;

public class DayListRequestBody {
	@Min( 1L )
	public long tripId;

	public DayListRequestBody() {
	}

	public DayListRequestBody( long tripId ) {
		this.tripId = tripId;
	}

	public long getTripId() {
		return tripId;
	}

	public void setTripId( long tripId ) {
		this.tripId = tripId;
	}

	@Override
	public String toString() {
		return "DayListRequestBody [dayId=" + tripId + "]";
	}

}
