package com.axisdesktop.crawler.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.axisdesktop.crawler.entity.Provider;
import com.axisdesktop.crawler.entity.ProviderFeedUri;
import com.axisdesktop.crawler.repository.ProviderFeedUriRepository;
import com.axisdesktop.crawler.repository.ProviderRepository;
import com.axisdesktop.crawler.service.ProviderService;

@Service
public class ProviderServiceImpl implements ProviderService {
	private ProviderRepository provRepo;
	private ProviderFeedUriRepository provFeedUriRepo;

	@Autowired
	public ProviderServiceImpl( ProviderRepository provRepo, ProviderFeedUriRepository provFeedUriRepo ) {
		this.provRepo = provRepo;
		this.provFeedUriRepo = provFeedUriRepo;
	}

	@Override
	public List<Provider> findAll() {
		return provRepo.findAll();
	}

	@Override
	public Provider load( int id ) {
		return provRepo.findOne( id );
	}

	@Override
	public List<Provider> findProviderByStatusId( int id ) {
		return provRepo.findByStatusId( id );
	}

	@Override
	public List<ProviderFeedUri> findActiveFeedUri( int providerId ) {
		return provFeedUriRepo.getActive( providerId );
	}

}
