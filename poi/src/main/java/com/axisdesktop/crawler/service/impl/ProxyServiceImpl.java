package com.axisdesktop.crawler.service.impl;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.axisdesktop.crawler.entity.Proxy;
import com.axisdesktop.crawler.repository.ProxyRepository;
import com.axisdesktop.crawler.service.ProxyService;

@Service
public class ProxyServiceImpl implements ProxyService {
	@Autowired
	private ProxyRepository proxyRepo;

	@Override
	public List<Proxy> findAll() {
		return proxyRepo.findAll();
	}

	@Override
	public Proxy load( int id ) {
		return proxyRepo.findOne( id );
	}

	@Override
	public Proxy getRandomActiveProxy() {
		List<Proxy> l = proxyRepo.getRandomActiveProxy( Calendar.getInstance(), new PageRequest( 0, 1 ) );

		if( l != null && l.size() > 0 ) {
			return l.get( 0 );
		}

		return null;// Calendar.getInstance()
	}

}
