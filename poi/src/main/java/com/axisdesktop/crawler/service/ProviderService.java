package com.axisdesktop.crawler.service;

import java.util.List;

import com.axisdesktop.crawler.entity.Provider;
import com.axisdesktop.crawler.entity.ProviderData;
import com.axisdesktop.crawler.entity.ProviderUrl;

public interface ProviderService {
	List<Provider> findAll();

	List<Provider> findByStatusId( int id );

	Provider load( int id );

	ProviderUrl loadUrl( long id );

	ProviderUrl loadUrl( int providerId, String url );

	ProviderUrl createUrl( ProviderUrl pu );

	ProviderUrl updateUrl( ProviderUrl pu );

	void deleteUrl( long id );

	List<ProviderUrl> findActiveFeedUrl( int providerId );

	List<ProviderUrl> findUrlForUpdate( int providerId );

	boolean isUrlExist( int providerId, String url );

	ProviderData saveProviderData( ProviderData data );

}
