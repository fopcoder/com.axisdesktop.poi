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
import javax.persistence.Transient;

@Entity
@Table( name = "provider_feed_uri", schema = "crawler" )
public class ProviderFeedUri {
	@Id
	@GeneratedValue
	private int id;

	private int provider_id;

	@ManyToOne( fetch = FetchType.LAZY )
	@JoinColumn( name = "status_id" )
	private ProviderFeedUriStatus status;

	@Transient
	@Column( name = "status_id" )
	private int statusId;

	private String uri;
	private String description;
	private String log;

	@Column( updatable = false )
	@Temporal( TemporalType.TIMESTAMP )
	private Calendar created;

	@Temporal( TemporalType.TIMESTAMP )
	private Calendar modified;

	@Temporal( TemporalType.TIMESTAMP )
	private Calendar fetched;

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

	public int getProvider_id() {
		return provider_id;
	}

	public void setProvider_id( int provider_id ) {
		this.provider_id = provider_id;
	}

	public ProviderFeedUriStatus getStatus() {
		return status;
	}

	public void setStatus( ProviderFeedUriStatus status ) {
		this.status = status;
	}

	public String getUri() {
		return uri;
	}

	public void setUri( String uri ) {
		this.uri = uri;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription( String description ) {
		this.description = description;
	}

	public String getLog() {
		return log;
	}

	public void setLog( String log ) {
		this.log = log;
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

	public Calendar getFetched() {
		return fetched;
	}

	public void setFetched( Calendar fetched ) {
		this.fetched = fetched;
	}

	public int getStatusId() {
		return statusId;
	}

	public void setStatusId( int statusId ) {
		this.statusId = statusId;
	}

	@Override
	public String toString() {
		return "ProviderFeedUri [id=" + id + ", provider_id=" + provider_id + ", status=" + status + ", uri=" + uri
				+ ", description=" + description + ", log=" + log + ", created=" + created + ", modified=" + modified
				+ ", fetched=" + fetched + "]";
	}

}
