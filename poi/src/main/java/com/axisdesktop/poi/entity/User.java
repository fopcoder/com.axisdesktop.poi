package com.axisdesktop.poi.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.axisdesktop.base.entity.BaseEntityName;

@Entity
@Table( name = "user" )
public class User extends BaseEntityName<Long> {
	private String email;
	private String password;

	@Column( name = "first_name" )
	private String firstName;

	@Column( name = "last_name" )
	private String lastName;

	@Column( name = "status_id" )
	private int statusId;

	public String getEmail() {
		return email;
	}

	public void setEmail( String email ) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword( String password ) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName( String firstName ) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName( String lastName ) {
		this.lastName = lastName;
	}

	public int getStatusId() {
		return statusId;
	}

	public void setStatusId( int statusId ) {
		this.statusId = statusId;
	}

	@Override
	public String toString() {
		return "User [email=" + email + ", password=" + password + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", statusId=" + statusId + ", toString()=" + super.toString() + "]";
	}

}
