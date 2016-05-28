package com.axisdesktop.crawler.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.axisdesktop.base.entity.SimpleEntity;

import ch.rasc.extclassgenerator.Model;

@Entity
@Table( name = "proxy_status", schema = "crawler" )
@Model( value = "Crawler.proxy.model.ProxyStatus", totalProperty = "total", rootProperty = "records", successProperty = "success", //
		readMethod = "Crawler.proxyService.statusList" )
public class CrawlerProxyStatus extends SimpleEntity<Integer> {
	@Override
	public String toString() {
		return "CrawlerProxyStatus [ " + super.toString() + " ]";
	}
}
