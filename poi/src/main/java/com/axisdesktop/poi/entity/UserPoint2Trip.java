package com.axisdesktop.poi.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table( name = "user_point2trip", schema = "poi" )
public class UserPoint2Trip {
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private long id;

	@ManyToOne( fetch = FetchType.LAZY )
	private Trip trip;

	@Column( name = "trip_id", insertable = false, updatable = false )
	private long tripId;

	@ManyToOne( fetch = FetchType.LAZY )
	private UserPoint point;

	@Column( name = "point_id", insertable = false, updatable = false )
	private long pointId;

	private int porder;

	public long getId() {
		return id;
	}

	public void setId( long id ) {
		this.id = id;
	}

	public Trip getTrip() {
		return trip;
	}

	public void setTrip( Trip trip ) {
		this.trip = trip;
	}

	public long getTripId() {
		return tripId;
	}

	public void setTripId( long tripId ) {
		this.tripId = tripId;
	}

	public UserPoint getPoint() {
		return point;
	}

	public void setPoint( UserPoint point ) {
		this.point = point;
	}

	public long getPointId() {
		return pointId;
	}

	public void setPointId( long pointId ) {
		this.pointId = pointId;
	}

	public int getPorder() {
		return porder;
	}

	public void setPorder( int porder ) {
		this.porder = porder;
	}

	@Override
	public String toString() {
		return "UserPoint2Trip [id=" + id + ", tripId=" + tripId + ", pointId=" + pointId + ", porder=" + porder + "]";
	}

}
