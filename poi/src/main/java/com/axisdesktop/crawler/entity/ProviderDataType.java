package com.axisdesktop.crawler.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.axisdesktop.base.entity.SimpleEntity;

import ch.rasc.extclassgenerator.Model;

@Entity
@Table( name = "provider_data_type", schema = "crawler" )
@Model( value = "Crawler.data.model.DataType", totalProperty = "total", rootProperty = "records", successProperty = "success", //
		readMethod = "Crawler.dataService.typeList" )
public class ProviderDataType extends SimpleEntity<Integer> {
	@Override
	public String toString() {
		return "ProviderDataType [" + super.toString() + " ]";
	}
}
