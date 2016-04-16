package com.axisdesktop.poi.entity;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.axisdesktop.base.utils.DateUtils;
import com.vividsolutions.jts.geom.Point;

@Entity
@Table( name = "location", schema = "poi" )

public class Location {
	@Id
	@GeneratedValue
	private long id;

	private Point point;

	@Column( updatable = false )
	@Temporal( TemporalType.TIMESTAMP )
	private Calendar created;

	@PrePersist
	private void prePersist() {
		this.created = Calendar.getInstance();
	}

	public long getId() {
		return id;
	}

	public void setId( long id ) {
		this.id = id;
	}

	public Point getPoint() {
		return point;
	}

	public void setPoint( Point point ) {
		this.point = point;
	}

	public Calendar getCreated() {
		return created;
	}

	public void setCreated( Calendar created ) {
		this.created = created;
	}

	@Override
	public String toString() {
		return "Location [id=" + id + ", point=" + point + ", created=" + DateUtils.calendarToString( created ) + "]";
	}
}
