package com.axisdesktop.crawler.entity;

import static com.axisdesktop.utils.Utils.calendarToString;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table( name = "provider_url", schema = "crawler" )
@NamedQueries( {
		@NamedQuery( name = "ProviderUrl.findActiveFeedUrl", query = "SELECT u FROM ProviderUrl u WHERE providerId = :providerId AND ( statusId = 1 OR ( statusId = 3 AND fetched < :waitFor AND tries < :maxTries ) ) AND typeId = 1" ),
		@NamedQuery( name = "ProviderUrl.checkByProviderIdAndUrl", query = "SELECT 1 > 0 FROM ProviderUrl WHERE providerId = :providerId AND url LIKE :url" ) } )
public class ProviderUrl {
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private long id;

	@Column( name = "provider_id" )
	private int providerId;

	@ManyToOne( fetch = FetchType.LAZY )
	@JoinColumn( name = "status_id", insertable = false, updatable = false )
	private ProviderUrlStatus status;

	@Column( name = "status_id" )
	private int statusId;

	@ManyToOne( fetch = FetchType.LAZY )
	@JoinColumn( name = "type_id", insertable = false, updatable = false )
	private ProviderUrlType type;

	@Column( name = "type_id" )
	private int typeId;

	private String url;
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

	public long getId() {
		return id;
	}

	public void setId( long id ) {
		this.id = id;
	}

	public int getProviderId() {
		return providerId;
	}

	public void setProviderId( int providerId ) {
		this.providerId = providerId;
	}

	public ProviderUrlStatus getStatus() {
		return status;
	}

	public void setStatus( ProviderUrlStatus status ) {
		this.status = status;
	}

	public int getStatusId() {
		return statusId;
	}

	public void setStatusId( int statusId ) {
		this.statusId = statusId;
	}

	public ProviderUrlType getType() {
		return type;
	}

	public void setType( ProviderUrlType type ) {
		this.type = type;
	}

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId( int typeId ) {
		this.typeId = typeId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl( String url ) {
		this.url = url;
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

	public int getTries() {
		return tries;
	}

	public void setTries( int tries ) {
		this.tries = tries;
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

	@Override
	public String toString() {
		return "ProviderUrl [id=" + id + ", providerId=" + providerId + ", statusId=" + statusId + ", url=" + url
				+ ", description=" + description + ", log=" + log + ", tries=" + tries + ", created="
				+ calendarToString( created ) + ", modified=" + calendarToString( modified ) + ", fetched="
				+ calendarToString( fetched ) + ", typeId=" + typeId + "]";
	}

}
