package com.axisdesktop.crawler.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table( name = "provider_uri_status", schema = "crawler" )
public class ProviderUriStatus extends AbstractStatus {
	@Override
	public String toString() {
		return "ProviderUriStatus" + super.toString();
	}
}
