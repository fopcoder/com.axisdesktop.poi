package com.axisdesktop.poi.helper;

import javax.validation.constraints.Min;

public class DayPointListRequestBody {
	@Min( 1L )
	public long tripId;

	@Min( 1L )
	public long dayId;

	public DayPointListRequestBody() {
	}

	public DayPointListRequestBody( long tripId, long dayId ) {
		this.tripId = tripId;
		this.dayId = dayId;
	}

	public long getTripId() {
		return tripId;
	}

	public void setTripId( long tripId ) {
		this.tripId = tripId;
	}

	public long getDayId() {
		return dayId;
	}

	public void setDayId( long dayId ) {
		this.dayId = dayId;
	}

	@Override
	public String toString() {
		return "DayPointListRequestBody [dayId=" + tripId + ", dayId=" + dayId + "]";
	}

}
