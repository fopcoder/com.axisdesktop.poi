package com.axisdesktop.poi.helper;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

public class NewUserPointHelper {
	@NotNull
	public Double latitude, longitude;

	@NotEmpty
	public String name;

	public String description;

	public Long pointId;

	@Override
	public String toString() {
		return "NewUserPointHelper [latitude=" + latitude + ", longitude=" + longitude + ", name=" + name
				+ ", description=" + description + ", pointId=" + pointId + "]";
	}

}
