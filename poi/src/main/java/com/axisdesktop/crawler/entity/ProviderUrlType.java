package com.axisdesktop.crawler.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table( name = "provider_url_type", schema = "crawler" )
public class ProviderUrlType extends AbstractSimpleData {
	@Override
	public String toString() {
		return "ProviderUrlType" + super.toString();
	}
}
