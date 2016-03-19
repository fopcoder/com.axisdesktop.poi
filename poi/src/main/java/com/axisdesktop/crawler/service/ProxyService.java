package com.axisdesktop.crawler.service;

import java.util.List;

import com.axisdesktop.crawler.entity.Proxy;

public interface ProxyService {
	List<Proxy> findAll();

	Proxy getRandomActiveProxy();

	Proxy load( int id );
}
