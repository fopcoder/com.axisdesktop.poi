package com.axisdesktop.poi.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.PostLoad;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.axisdesktop.base.entity.SimpleEntity;
import com.axisdesktop.poi.helper.GeometrySerializer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.vividsolutions.jts.geom.Point;

@Entity
@Table( schema = "poi", name = "user_point" )
public class UserPoint extends SimpleEntity<Long> {
	@Column( name = "user_id" )
	private long userId;

	@JsonSerialize( using = GeometrySerializer.class )
	// @JsonDeserialize( using = JsonToPointDeserializer.class )
	private Point point;

	@Column( name = "point_id" )
	private Long pointId;

	@JsonIgnore
	@OneToMany( fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "point" )
	List<UserPoint2Trip> point2trip = new ArrayList<>();

	@Transient
	private double latitude;

	@Transient
	private double longitude;

	@PostLoad
	void postLoad() {
		latitude = point.getY();
		longitude = point.getX();
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId( long userId ) {
		this.userId = userId;
	}

	public Point getPoint() {
		return point;
	}

	public void setPoint( Point point ) {
		this.point = point;
	}

	public Long getPointId() {
		return pointId;
	}

	public void setPointId( Long pointId ) {
		this.pointId = pointId;
	}

	public List<UserPoint2Trip> getPoint2trip() {
		return point2trip;
	}

	public void setPoint2trip( List<UserPoint2Trip> point2trip ) {
		this.point2trip = point2trip;
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

	@Override
	public String toString() {
		return "UserPoint [" + super.toString() + "userId=" + userId + ", point=" + point + ", pointId=" + pointId
				+ ", latitude=" + latitude + ", longitude=" + longitude + "]";
	}

}
