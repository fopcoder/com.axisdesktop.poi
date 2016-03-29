package com.axisdesktop.crawler.entity;

import static com.axisdesktop.utils.DateUtils.calendarToString;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

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

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

@Entity
@Table( name = "provider_url", schema = "crawler" )
@TypeDef( name = "hstore", typeClass = HstoreUserType.class )
@NamedQueries( {
		@NamedQuery( name = "ProviderUrl.findActiveFeedUrl", query = "SELECT u FROM ProviderUrl u WHERE providerId = :providerId AND ( statusId = 1 OR ( statusId = 3 AND fetched < :waitFor AND tries < :maxTries ) ) AND typeId = 1" ),
		@NamedQuery( name = "ProviderUrl.isExistByProviderIdAndUrl", query = "SELECT COUNT(*) > 0 FROM ProviderUrl WHERE providerId = :providerId AND url LIKE :url" ),
		@NamedQuery( name = "ProviderUrl.fidUrlForUpdate", query = "SELECT u FROM ProviderUrl u WHERE providerId = :providerId AND typeId IN(2,3) AND statusId IN(4,6)" ) } )
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
	private String log;
	private int tries;

	@Column( name = "parent_id" )
	private long parentId;

	@Column( updatable = false )
	@Temporal( TemporalType.TIMESTAMP )
	private Calendar created;

	@Temporal( TemporalType.TIMESTAMP )
	private Calendar modified;

	@Temporal( TemporalType.TIMESTAMP )
	private Calendar fetched;

	@Type( type = "hstore" )
	private Map<String, Object> params;

	@PrePersist
	private void prePersist() {
		this.created = this.modified = Calendar.getInstance();
		if( params == null ) params = new HashMap<>();
	}

	@PreUpdate
	private void preUpdate() {
		this.modified = Calendar.getInstance();
		if( params == null ) params = new HashMap<>();
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

	public long getParentId() {
		return parentId;
	}

	public void setParentId( long parentId ) {
		this.parentId = parentId;
	}

	@Override
	public String toString() {
		return "ProviderUrl [id=" + id + ", providerId=" + providerId + ", statusId=" + statusId + ", url=" + url
				+ ", log=" + log + ", tries=" + tries + ", created=" + calendarToString( created ) + ", modified="
				+ calendarToString( modified ) + ", fetched=" + calendarToString( fetched ) + ", typeId=" + typeId
				+ ", parentId=" + parentId + ", params=" + params + "]";
	}

}
