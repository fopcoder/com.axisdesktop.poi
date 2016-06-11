package com.axisdesktop.crawler.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.axisdesktop.crawler.entity.ProviderData;
import com.axisdesktop.crawler.repository.BaseRepository;
import com.axisdesktop.crawler.repository.ProviderDataRepository;
import com.axisdesktop.crawler.service.ProviderDataService;

@Service
public class ProviderDataServiceImpl extends BaseServiceImpl<ProviderData, Long> implements ProviderDataService {
	private static final Logger logger = LoggerFactory.getLogger( ProviderDataServiceImpl.class );

	private ProviderDataRepository provRepo;
	private Environment env;

	public ProviderDataServiceImpl() {
	}

	@Autowired
	public ProviderDataServiceImpl( ProviderDataRepository provRepo, Environment env ) {
		this.provRepo = provRepo;
		this.env = env;
	}

	@Override
	public BaseRepository<ProviderData, Long> getRepository() {
		return this.provRepo;
	}

}
