package com.axisdesktop.poi.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import com.axisdesktop.base.entity.SimpleEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table( name = "trip", schema = "poi" )
public class Trip extends SimpleEntity<Long> {
	@Column( name = "parent_id" )
	private Long parentId;

	@Column( name = "user_id" )
	private long userId;

	private int ord;

	@JsonIgnore
	@OneToMany( fetch = FetchType.LAZY, mappedBy = "trip", cascade = CascadeType.ALL )
	@OrderBy( "porder" )
	Set<UserPoint2Trip> point2trip = new HashSet<>();

	public Long getParentId() {
		return parentId;
	}

	public void setParentId( Long parentId ) {
		this.parentId = parentId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId( long userId ) {
		this.userId = userId;
	}

	public int getOrd() {
		return ord;
	}

	public void setOrd( int ord ) {
		this.ord = ord;
	}

	public Set<UserPoint2Trip> getPoint2trip() {
		return point2trip;
	}

	public void setPoint2trip( Set<UserPoint2Trip> point2trip ) {
		this.point2trip = point2trip;
	}

	@Override
	public String toString() {
		return "Trip [" + super.toString() + ", parentId=" + parentId + ", userId=" + userId + ", ord=" + ord + "]";
	}

}
