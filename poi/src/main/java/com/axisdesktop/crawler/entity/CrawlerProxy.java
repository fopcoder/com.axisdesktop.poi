package com.axisdesktop.crawler.entity;

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

import static com.axisdesktop.utils.Utils.*;

@Entity
@Table( name = "proxy", schema = "crawler" )
@NamedQueries( {
		@NamedQuery( name = "CrawlerProxy.findActiveOrderByRandom", query = "SELECT p FROM CrawlerProxy p WHERE statusId = 1 OR ( statusId = 3 AND fetched < :waitFor AND tries < :maxTries ) ORDER BY RANDOM()" ) } )
public class CrawlerProxy {
	@Id
	@GeneratedValue
	private int id;

	private String host;
	private int port;

	@Column( name = "`user`" )
	private String user;

	private String password;
	private String log;
	private int tries;

	@Column( updatable = false )
	@Temporal( TemporalType.TIMESTAMP )
	private Calendar created;

	@Temporal( TemporalType.TIMESTAMP )
	private Calendar modified;

	@Temporal( TemporalType.TIMESTAMP )
	private Calendar fetched;

	@ManyToOne( fetch = FetchType.LAZY )
	@JoinColumn( name = "status_id", insertable = false, updatable = false )
	private CrawlerProxyStatus crawlerProxyStatus;

	@Column( name = "status_id" )
	private int statusId;

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

	public String getHost() {
		return host;
	}

	public void setHost( String host ) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort( int port ) {
		this.port = port;
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

	public CrawlerProxyStatus getProxyStatus() {
		return crawlerProxyStatus;
	}

	public void setProxyStatus( CrawlerProxyStatus crawlerProxyStatus ) {
		this.crawlerProxyStatus = crawlerProxyStatus;
	}

	public String getUser() {
		return user;
	}

	public void setUser( String user ) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword( String password ) {
		this.password = password;
	}

	public int getStatusId() {
		return statusId;
	}

	public void setStatusId( int status_id ) {
		this.statusId = status_id;
	}

	public String getLog() {
		return log;
	}

	public void setLog( String log ) {
		this.log = log;
	}

	public Calendar getFetched() {
		return fetched;
	}

	public void setFetched( Calendar fetched ) {
		this.fetched = fetched;
	}

	public int getTries() {
		return tries;
	}

	public void setTries( int tries ) {
		this.tries = tries;
	}

	@Override
	public String toString() {
		return "CrawlerProxy [id=" + id + ", host=" + host + ", port=" + port + ", user=" + user + ", password=" + password
				+ ", log=" + log + ", created=" + calendarToString( created ) + ", modified="
				+ calendarToString( modified ) + ", fetched=" + calendarToString( fetched ) + ", status_id=" + statusId
				+ ", tries=" + tries + "]";
	}
}
