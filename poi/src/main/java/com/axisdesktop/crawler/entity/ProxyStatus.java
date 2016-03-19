package com.axisdesktop.crawler.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table( name = "proxy_status", schema = "crawler" )
public class ProxyStatus extends AbstractStatus {
	@Override
	public String toString() {
		return "ProxyStatus" + super.toString();
	}

}