package com.axisdesktop.crawler.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table( name = "provider_status", schema = "crawler" )
public class ProviderStatus extends AbstractSimpleData {
	@Override
	public String toString() {
		return "ProviderStatus" + super.toString();
	}
}
