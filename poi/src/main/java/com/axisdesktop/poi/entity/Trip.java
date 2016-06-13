package com.axisdesktop.poi.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
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

	@Override
	public String toString() {
		return "Trip [" + super.toString() + ", parentId=" + parentId + ", userId=" + userId + ", ord=" + ord + "]";
	}

}
