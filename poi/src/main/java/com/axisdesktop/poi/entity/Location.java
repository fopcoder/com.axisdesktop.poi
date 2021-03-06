package com.axisdesktop.poi.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.axisdesktop.base.entity.BaseEntity;
import com.vividsolutions.jts.geom.Point;

@Entity
@Table( name = "location", schema = "poi" )
public class Location extends BaseEntity<Long> {

	private Point point;

	@Column( name = "status_id" )
	private int statusId;

	@ManyToOne( fetch = FetchType.LAZY )
	@JoinColumn( name = "status_id", insertable = false, updatable = false )
	private RoleStatus status;

	private double rating;

	private String link;

	@OneToMany( fetch = FetchType.LAZY, mappedBy = "locationId" )
	private List<LocationComment> comments;

	public Point getPoint() {
		return point;
	}

	public void setPoint( Point point ) {
		this.point = point;
	}

	public int getStatusId() {
		return statusId;
	}

	public void setStatusId( int statusId ) {
		this.statusId = statusId;
	}

	public RoleStatus getStatus() {
		return status;
	}

	public void setStatus( RoleStatus status ) {
		this.status = status;
	}

	public double getRating() {
		return rating;
	}

	public void setRating( double rating ) {
		this.rating = rating;
	}

	public String getLink() {
		return link;
	}

	public void setLink( String link ) {
		this.link = link;
	}

	@Override
	public String toString() {
		return "Location [" + ", point=" + point + ", created=" + "]";
	}
}
