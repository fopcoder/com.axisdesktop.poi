package com.axisdesktop.crawler.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table( name = "provider_url_status", schema = "crawler" )
public class ProviderUrlStatus extends AbstractSimpleData {
	@Override
	public String toString() {
		return "ProviderUrlStatus" + super.toString();
	}
}
