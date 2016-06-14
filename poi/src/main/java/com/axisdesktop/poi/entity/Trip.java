package com.axisdesktop.poi.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.axisdesktop.base.entity.SimpleEntity;

@Entity
@Table( name = "trip", schema = "poi" )
public class Trip extends SimpleEntity<Long> {
	@Column( name = "parent_id" )
	private Long parentId;

	@Column( name = "user_id" )
	private long userId;

	private int ord;

	@OneToMany( fetch = FetchType.LAZY )
	@JoinTable( name = "user_point2trip", schema = "poi", joinColumns = {
			@JoinColumn( name = "trip_id", referencedColumnName = "id" ) }, inverseJoinColumns = {
					@JoinColumn( name = "point_id", referencedColumnName = "id" ) } )
	List<UserPoint> points;

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

	public List<UserPoint> getPoints() {
		return points;
	}

	public void setPoints( List<UserPoint> points ) {
		this.points = points;
	}

	@Override
	public String toString() {
		return "Trip [" + super.toString() + ", parentId=" + parentId + ", userId=" + userId + ", ord=" + ord + "]";
	}

}
