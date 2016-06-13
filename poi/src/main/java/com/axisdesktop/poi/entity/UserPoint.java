package com.axisdesktop.poi.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.axisdesktop.base.entity.SimpleEntity;
import com.vividsolutions.jts.geom.Point;

@Entity
@Table( schema = "poi", name = "user_point" )
public class UserPoint extends SimpleEntity<Long> {
	@Column( name = "user_id" )
	private long userId;

	private Point point;

	@Column( name = "point_id" )
	private Long pointId;

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

}
