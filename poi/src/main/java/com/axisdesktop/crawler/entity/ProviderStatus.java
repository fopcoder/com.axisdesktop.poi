package com.axisdesktop.crawler.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.axisdesktop.base.entity.SimpleEntity;

import ch.rasc.extclassgenerator.Model;

@Entity
@Table( name = "provider_status", schema = "crawler" )
@Model( value = "Crawler.provider.model.ProviderStatus", totalProperty = "total", rootProperty = "records", successProperty = "success", //
		readMethod = "Crawler.providerService.statusList" )
public class ProviderStatus extends SimpleEntity<Integer> {
	@Override
	public String toString() {
		return "ProviderStatus [" + super.toString() + " ]";
	}
}
