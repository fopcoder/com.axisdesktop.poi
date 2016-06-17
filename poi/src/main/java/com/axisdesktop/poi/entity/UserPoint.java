package com.axisdesktop.poi.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.axisdesktop.base.entity.SimpleEntity;
import com.axisdesktop.poi.helper.JsonToPointDeserializer;
import com.axisdesktop.poi.helper.PointToJsonSerializer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.vividsolutions.jts.geom.Point;

@Entity
@Table( schema = "poi", name = "user_point" )
public class UserPoint extends SimpleEntity<Long> {
	@Column( name = "user_id" )
	private long userId;

	@JsonSerialize( using = PointToJsonSerializer.class )
	@JsonDeserialize( using = JsonToPointDeserializer.class )
	private Point point;

	@Column( name = "point_id" )
	private Long pointId;

	@JsonIgnore
	@OneToMany( fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "point" )
	Set<UserPoint2Trip> point2trip = new HashSet<>();

	public Long getPointId() {
		return pointId;
	}

	public void setPointId( Long pointId ) {
		this.pointId = pointId;
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

	public Set<UserPoint2Trip> getTrips() {
		return point2trip;
	}

	public void setTrips( Set<UserPoint2Trip> trips ) {
		this.point2trip = trips;
	}

	@Override
	public String toString() {
		return "UserPoint [" + super.toString() + ", userId=" + userId + ", point=" + point + ", pointId=" + pointId
				+ "]";
	}

}
