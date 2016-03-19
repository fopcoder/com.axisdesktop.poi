package com.axisdesktop.crawler.service;

import java.util.List;

import com.axisdesktop.crawler.entity.Provider;

public interface ProviderService {
	List<Provider> findAll();

	List<Provider> findByStatusId( int id );

	Provider load( int id );
}
