package com.axisdesktop.crawler.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table( name = "provider_feed_uri_status", schema = "crawler" )
public class ProviderFeedUriStatus extends AbstractStatus {
	@Override
	public String toString() {
		return "ProviderFeedUriStatus" + super.toString();
	}
}
