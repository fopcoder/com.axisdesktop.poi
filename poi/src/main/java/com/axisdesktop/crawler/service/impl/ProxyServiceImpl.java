package com.axisdesktop.crawler.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.axisdesktop.crawler.entity.Proxy;
import com.axisdesktop.crawler.repository.ProxyRepository;
import com.axisdesktop.crawler.service.ProxyService;

@Service
// @Transactional( readOnly = true )
public class ProxyServiceImpl implements ProxyService {
	@Autowired
	private ProxyRepository proxyRepo;

	@Override
	public List<Proxy> findAll() {
		return proxyRepo.findAll();
	}

	@Override
	public Proxy getRandom() {

		// proxyRepo.find
		// return proxyRepo.findOneRandom();
		return null;
	}

	@Override
	public Proxy load( int id ) {
		return proxyRepo.findOne( id );
	}

}
