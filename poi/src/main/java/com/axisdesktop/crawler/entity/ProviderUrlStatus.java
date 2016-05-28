package com.axisdesktop.crawler.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.axisdesktop.base.entity.SimpleEntity;

import ch.rasc.extclassgenerator.Model;

@Entity
@Table( name = "provider_url_status", schema = "crawler" )
@Model( value = "Crawler.url.model.UrlStatus", totalProperty = "total", rootProperty = "records", successProperty = "success", //
		readMethod = "Crawler.urlService.statusList" )
public class ProviderUrlStatus extends SimpleEntity<Integer> {
	@Override
	public String toString() {
		return "ProviderUrlStatus [ " + super.toString() + " ]";
	}
}
