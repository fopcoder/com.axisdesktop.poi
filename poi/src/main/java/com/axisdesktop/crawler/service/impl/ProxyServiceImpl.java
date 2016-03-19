package com.axisdesktop.crawler.service.impl;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.axisdesktop.crawler.entity.Proxy;
import com.axisdesktop.crawler.entity.ProxyStatus;
import com.axisdesktop.crawler.repository.ProxyRepository;
import com.axisdesktop.crawler.repository.ProxyStatusRepository;
import com.axisdesktop.crawler.service.ProxyService;

@Service
public class ProxyServiceImpl implements ProxyService {
	private ProxyRepository proxyRepo;
	private ProxyStatusRepository proxyStausRepo;

	@Autowired
	public ProxyServiceImpl( ProxyRepository proxyRepo, ProxyStatusRepository proxyStausRepo ) {
		this.proxyRepo = proxyRepo;
		this.proxyStausRepo = proxyStausRepo;
	}

	@Override
	public List<Proxy> findAll() {
		return proxyRepo.findAll();
	}

	@Override
	public Proxy load( int id ) {
		if( id <= 0 ) throw new IllegalArgumentException( "proxyId must be > 0" );
		return proxyRepo.findOne( id );
	}

	@Override
	public Proxy update( Proxy proxy ) {
		if( proxy == null ) throw new IllegalArgumentException( "proxy is null" );
		if( proxy.getId() == 0 ) throw new IllegalArgumentException( "can't update new proxy" );
		if( this.proxyRepo.findOne( proxy.getId() ) == null )
			throw new IllegalArgumentException( "proxy " + proxy + " not found" );

		return this.proxyRepo.save( proxy );
	}

	@Override
	public Proxy getRandomActiveProxy() {
		Calendar cal = Calendar.getInstance();
		cal.add( Calendar.MINUTE, -15 );

		List<Proxy> l = proxyRepo.getRandomActiveProxy( cal, new PageRequest( 0, 1 ) );

		if( l != null && l.size() > 0 ) {
			return l.get( 0 );
		}

		return null;
	}

	@Override
	public ProxyStatus getProxyStatusById( int id ) {
		return proxyStausRepo.findOne( id );
	}

}