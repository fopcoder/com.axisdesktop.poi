package com.axisdesktop.poi.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table( name = "user_point2trip", schema = "poi" )
public class UserPoint2Trip {
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private long id;

	@ManyToOne( fetch = FetchType.LAZY )
	private Trip trip;

	@Transient
	@Column( name = "trip_id" )
	private long tripId;

	@ManyToOne( fetch = FetchType.LAZY )
	private UserPoint point;

	@Transient
	@Column( name = "point_id" )
	private long pointId;

	private int porder;

	public long getId() {
		return id;
	}

	public void setId( long id ) {
		this.id = id;
	}

	public int getPorder() {
		return porder;
	}

	public void setPorder( int porder ) {
		this.porder = porder;
	}

	public Trip getTrip() {
		return trip;
	}

	public void setTrip( Trip trip ) {
		this.trip = trip;
	}

	public UserPoint getPoint() {
		return point;
	}

	public void setPoint( UserPoint point ) {
		this.point = point;
	}

	public long getTripId() {
		return tripId;
	}

	public void setTripId( long tripId ) {
		this.tripId = tripId;
	}

	public long getPointId() {
		return pointId;
	}

	public void setPointId( long pointId ) {
		this.pointId = pointId;
	}

	@Override
	public String toString() {
		return "UserPoint2Trip [id=" + id + ", tripId=" + tripId + ", pointId=" + pointId + ", porder=" + porder + "]";
	}

}
