package com.axisdesktop.poi.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.axisdesktop.base.entity.SimpleEntity;

@Entity
@Table( name = "user_role" )
public class UserRole extends SimpleEntity<Integer> {
	@Column( name = "status_id" )
	private int statusId;
}
