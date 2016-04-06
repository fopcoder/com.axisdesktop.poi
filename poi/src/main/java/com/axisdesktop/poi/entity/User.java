package com.axisdesktop.poi.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.springframework.beans.BeanUtils;

import com.axisdesktop.base.entity.BaseEntityName;

@Entity
@Table( name = "user", schema = "poi" )
@NamedQuery( name = "User.findRole", query = "SELECT r FROM Role r" )
public class User extends BaseEntityName<Long> {
	private String email;
	private String password;

	@Column( name = "first_name" )
	private String firstName;

	@Column( name = "last_name" )
	private String lastName;

	@Column( name = "status_id" )
	private int statusId;

	@ManyToOne( fetch = FetchType.LAZY )
	@JoinColumn( name = "status_id", insertable = false, updatable = false )
	private UserStatus status;

	@ManyToMany( fetch = FetchType.LAZY )
	@JoinTable( name = "poi.user2role", joinColumns = @JoinColumn( name = "user_id", referencedColumnName = "id" ), inverseJoinColumns = @JoinColumn( name = "role_id", referencedColumnName = "id" ) )
	private Set<Role> roles;

	public User() {
	}

	public User( User user ) {
		BeanUtils.copyProperties( user, this );
	}

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

	public Set<Role> getRoles() {
		return roles;
	}

	@Override
	public String toString() {
		return "User [email=" + email + ", password=" + password + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", statusId=" + statusId + ", toString()=" + super.toString() + "]";
	}

}
