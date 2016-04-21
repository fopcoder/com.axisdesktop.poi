package com.axisdesktop.poi.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.axisdesktop.base.entity.SimpleEntity;

@Entity
@Table( name = "role", schema = "poi" )
public class Role extends SimpleEntity<Integer> {
	@Column( name = "status_id" )
	private int statusId;

	@ManyToOne( fetch = FetchType.LAZY )
	@JoinColumn( name = "status_id", insertable = false, updatable = false )
	private RoleStatus status;

}
