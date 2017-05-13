package com.axisdesktop.base.entity;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.axisdesktop.base.utils.DateUtils;
import com.fasterxml.jackson.annotation.JsonFormat;

import ch.rasc.extclassgenerator.ModelField;

@MappedSuperclass
public class BaseEntity<ID> {
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private ID id;

	@Column( updatable = false )
	@JsonFormat( pattern = "yyyy-MM-dd HH:mm:ssZ" )
	@ModelField( dateFormat = "Y-m-d H:i:sO" )
	@Temporal( TemporalType.TIMESTAMP )
	private Calendar created;

	@JsonFormat( pattern = "yyyy-MM-dd HH:mm:ssZ" )
	@ModelField( dateFormat = "Y-m-d H:i:sO" )
	@Temporal( TemporalType.TIMESTAMP )
	private Calendar modified;

	@PrePersist
	private void prePersist() {
		this.created = this.modified = Calendar.getInstance();
	}

	@PreUpdate
	private void preUpdate() {
		this.modified = Calendar.getInstance();
	}

	public ID getId() {
		return id;
	}

	public void setId( ID id ) {
		this.id = id;
	}

	public Calendar getCreated() {
		return created;
	}

	public void setCreated( Calendar created ) {
		this.created = created;
	}

	public Calendar getModified() {
		return modified;
	}

	public void setModified( Calendar modified ) {
		this.modified = modified;
	}

	@Override
	public String toString() {
		return "id=" + id + ", created=" + DateUtils.calendarToString( created ) + ", modified="
				+ DateUtils.calendarToString( modified );
	}

}
