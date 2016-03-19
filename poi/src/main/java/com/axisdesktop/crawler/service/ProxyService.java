package com.axisdesktop.crawler.service;

import java.util.List;

import com.axisdesktop.crawler.entity.Proxy;
import com.axisdesktop.crawler.entity.ProxyStatus;

public interface ProxyService {
	List<Proxy> findAll();

	Proxy getRandomActiveProxy();

	Proxy load( int id );

	Proxy update( Proxy proxy );

	ProxyStatus getProxyStatusById( int id );

}
