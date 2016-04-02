package com.axisdesktop.poi.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.axisdesktop.base.entity.SimpleEntity;

@Entity
@Table( name = "user_role_status" )
public class UserRoleStatus extends SimpleEntity<Integer> {

	@Override
	public String toString() {
		return "UserRoleStatus [" + super.toString() + "]";
	}

}
