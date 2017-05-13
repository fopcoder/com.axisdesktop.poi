package com.axisdesktop.base.entity;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class BaseEntityName<ID> extends BaseEntity<ID> {
	private String name;

	public String getName() {
		return name;
	}

	public void setName( String name ) {
		this.name = name;
	}

	@Override
	public String toString() {
		return super.toString() + ", name=" + name;
	}
}
