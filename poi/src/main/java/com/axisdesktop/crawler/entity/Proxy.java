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
import javax.persistence.Transient;

@Entity
@Table( name = "proxy", schema = "crawler" )
@NamedQueries( { @NamedQuery( name = "Proxy.full", query = "SELECT p FROM Proxy p" ) } )
public class Proxy {
	@Id
	@GeneratedValue
	private int id;

	private String host;
	private int port;
	private String user;
	private String password;

	@Column( updatable = false )
	@Temporal( TemporalType.TIMESTAMP )
	private Calendar created;

	@Temporal( TemporalType.TIMESTAMP )
	private Calendar modified;

	@Temporal( TemporalType.TIMESTAMP )
	@Column( name = "last_online" )
	private Calendar lastOnline;

	@ManyToOne( fetch = FetchType.LAZY )
	@JoinColumn( name = "status_id" )
	private ProxyStatus proxyStatus;

	@Transient
	private int status_id;

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

	public ProxyStatus getProxyStatus() {
		return proxyStatus;
	}

	public void setProxyStatus( ProxyStatus proxyStatus ) {
		this.proxyStatus = proxyStatus;
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

	public int getStatus_id() {
		return status_id;
	}

	public void setStatus_id( int status_id ) {
		this.status_id = status_id;
	}

	@Override
	public String toString() {
		return "Proxy [id=" + id + ", host=" + host + ", port=" + port + ", user=" + user + ", password=" + password
				+ ", created=" + created.get( Calendar.DATE ) + ", modified=" + modified.get( Calendar.DATE )
				+ ", proxyStatus=" + status_id + "]";
	}
}
