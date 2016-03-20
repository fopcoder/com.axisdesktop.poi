package com.axisdesktop.crawler.service;

import java.util.List;

import com.axisdesktop.crawler.entity.Provider;
import com.axisdesktop.crawler.entity.ProviderUrl;

public interface ProviderService {
	List<Provider> findAll();

	List<Provider> findByStatusId( int id );

	Provider load( int id );

	List<ProviderUrl> findActiveFeedUri( int providerId );

	ProviderUrl loadUrl( long id );

	ProviderUrl createUrl( ProviderUrl pu );

	ProviderUrl updateUrl( ProviderUrl pu );

	void deleteUrl( long id );
}
