package com.axisdesktop.poi.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.axisdesktop.base.entity.SimpleEntity;

@Entity
@Table( name = "role_status", schema = "poi" )
public class RoleStatus extends SimpleEntity<Integer> {

	@Override
	public String toString() {
		return "RoleStatus [" + super.toString() + "]";
	}

}
