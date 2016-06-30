package com.axisdesktop.poi.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.NamedNativeQuery;
import javax.persistence.OneToMany;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;

import com.axisdesktop.base.entity.BaseEntity;
import com.axisdesktop.poi.helper.LocationInfo;
import com.vividsolutions.jts.geom.Point;

@Entity
@Table( name = "location", schema = "poi" )

@NamedNativeQuery( name = "loadLocationInfo", query = "SELECT l.id, ST_Y(l.point) AS latitude, ST_X(l.point) AS longitude, l.link, "
		+ "tr.name, tr.full_description description, tr.short_description shortDescription, tr.address "
		+ "FROM poi.location l LEFT JOIN poi.location_tr tr ON tr.location_id = l.id AND tr.language_id = 'ru' "
		+ "WHERE l.id = :id", resultSetMapping = "LocationInfo" )

@SqlResultSetMapping( name = "LocationInfo", classes = {
		@ConstructorResult( targetClass = LocationInfo.class, columns = { //
				@ColumnResult( name = "id", type = Long.class ), //
				@ColumnResult( name = "latitude", type = Double.class ), //
				@ColumnResult( name = "longitude", type = Double.class ), //
				@ColumnResult( name = "name", type = String.class ), //
				@ColumnResult( name = "description", type = String.class ), //
				@ColumnResult( name = "shortDescription", type = String.class ), //
				@ColumnResult( name = "link", type = String.class ), //
				@ColumnResult( name = "address", type = String.class ), //
		} ) } )

public class Location extends BaseEntity<Long> {

	private Point point;

	@Column( name = "status_id" )
	private int statusId;

	// @ManyToOne( fetch = FetchType.LAZY )
	// @JoinColumn( name = "status_id", insertable = false, updatable = false )
	// private RoleStatus status;

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

	// public RoleStatus getStatus() {
	// return status;
	// }
	//
	// public void setStatus( RoleStatus status ) {
	// this.status = status;
	// }

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
