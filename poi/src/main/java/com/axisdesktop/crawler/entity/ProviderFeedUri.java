package com.axisdesktop.crawler.entity;

import static com.axisdesktop.utils.Utils.calendarToString;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table( name = "provider_feed_uri", schema = "crawler" )
@NamedQueries( {
		@NamedQuery( name = "ProviderFeedUri.getActive", query = "SELECT u FROM ProviderFeedUri u WHERE providerId = :providerId AND statusId = 1" ) } )
public class ProviderFeedUri {
	@Id
	@GeneratedValue
	private int id;

	@Column( name = "provider_id" )
	private int providerId;

	@ManyToOne( fetch = FetchType.LAZY )
	@JoinColumn( name = "status_id" )
	private ProviderFeedUriStatus status;

	@Column( name = "status_id", insertable = false, updatable = false )
	private int statusId;

	private String uri;
	private String description;
	private String log;
	private int tries;

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

	public int getProviderId() {
		return providerId;
	}

	public void setProviderId( int provider_id ) {
		this.providerId = provider_id;
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

	public int getTries() {
		return tries;
	}

	public void setTries( int tries ) {
		this.tries = tries;
	}

	@Override
	public String toString() {
		return "ProviderFeedUri [id=" + id + ", providerId=" + providerId + ", statusId=" + statusId + ", uri=" + uri
				+ ", description=" + description + ", log=" + log + ", tries=" + tries + ", created="
				+ calendarToString( created ) + ", modified=" + calendarToString( modified ) + ", fetched="
				+ calendarToString( fetched ) + "]";
	}

}
