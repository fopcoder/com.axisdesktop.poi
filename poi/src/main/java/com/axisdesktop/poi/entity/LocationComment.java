package com.axisdesktop.poi.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.axisdesktop.base.entity.BaseEntity;

@Entity
@Table( name = "location_comment", schema = "poi" )
public class LocationComment extends BaseEntity<Long> {

	@Column( name = "status_id" )
	private int statusId;

	@ManyToOne( fetch = FetchType.LAZY )
	@JoinColumn( name = "status_id", insertable = false, updatable = false )
	private RoleStatus status;

	@Column( name = "location_id" )
	private long locationId;

	private String header;
	private String body;

	@Column( name = "language_id" )
	private String languageId;

	@Column( name = "user_id" )
	private long userId;

	@Column( name = "parent_id" )
	private long parentId;

	@Column( name = "external_id" )
	private long externalId;

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

	public long getLocationId() {
		return locationId;
	}

	public void setLocationId( long locationId ) {
		this.locationId = locationId;
	}

	public String getHeader() {
		return header;
	}

	public void setHeader( String header ) {
		this.header = header;
	}

	public String getBody() {
		return body;
	}

	public void setBody( String body ) {
		this.body = body;
	}

	public String getLanguageId() {
		return languageId;
	}

	public void setLanguageId( String languageId ) {
		this.languageId = languageId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId( long userId ) {
		this.userId = userId;
	}

	public long getParentId() {
		return parentId;
	}

	public void setParentId( long parentId ) {
		this.parentId = parentId;
	}

	public long getExternalId() {
		return externalId;
	}

	public void setExternalId( long externalId ) {
		this.externalId = externalId;
	}

}
