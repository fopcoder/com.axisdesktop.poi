package com.axisdesktop.poi.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.axisdesktop.base.entity.SimpleEntity;

@Entity
@Table( name = "user_status" )
public class UserStatus extends SimpleEntity<Integer> {

	@Override
	public String toString() {
		return "UserStatus [" + super.toString() + "]";
	}

}
