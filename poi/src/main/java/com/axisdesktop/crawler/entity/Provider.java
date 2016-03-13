package com.axisdesktop.crawler.entity;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table( name = "provider", schema = "crawler" )
public class Provider {
	@Id
	@GeneratedValue
	private int id;

	private String name;

	@Column( updatable = false )
	@Temporal( TemporalType.TIMESTAMP )
	private Calendar created;

	@Temporal( TemporalType.TIMESTAMP )
	private Calendar modified;

	private String description;

	@ManyToOne( fetch = FetchType.LAZY )
	@JoinColumn( name = "status_id" )
	private ProviderStatus providerStatus;

	@PrePersist
	private void prePersist() {
		this.created = this.modified = Calendar.getInstance();
	}

	@PreUpdate
	private void preUpdate() {
		this.modified = Calendar.getInstance();
	}

	public int getId() {
		return id;
	}

	public void setId( int id ) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName( String name ) {
		this.name = name;
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

	public String getDescription() {
		return description;
	}

	public void setDescription( String description ) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Provider [id=" + id + ", name=" + name + ", created=" + created + ", modified=" + modified
				+ ", description=" + description + "]";
	}

}
