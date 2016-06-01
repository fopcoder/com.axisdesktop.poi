package com.axisdesktop.crawler.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.axisdesktop.crawler.entity.ProviderDataType;
import com.axisdesktop.crawler.repository.BaseRepository;
import com.axisdesktop.crawler.repository.ProviderDataTypeRepository;

public class ProviderDataTypeServiceImpl extends BaseServiceImpl<ProviderDataType, Integer> {
	@Autowired
	private ProviderDataTypeRepository repo;

	@Override
	public BaseRepository<ProviderDataType, Integer> getRepository() {
		// TODO Auto-generated method stub
		return null;
	}
}
