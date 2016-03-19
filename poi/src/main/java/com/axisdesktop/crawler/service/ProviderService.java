package com.axisdesktop.crawler.service;

import java.util.List;

import com.axisdesktop.crawler.entity.Provider;
import com.axisdesktop.crawler.entity.ProviderFeedUri;

public interface ProviderService {
	List<Provider> findAll();

	List<Provider> findProviderByStatusId( int id );

	Provider load( int id );

	List<ProviderFeedUri> findActiveFeedUri( int providerId );
}
