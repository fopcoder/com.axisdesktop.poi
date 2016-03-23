package com.axisdesktop.crawler.service;

import java.util.List;

import com.axisdesktop.crawler.entity.CrawlerProxy;
import com.axisdesktop.crawler.entity.CrawlerProxyStatus;

public interface ProxyService {
	List<CrawlerProxy> findAll();

	CrawlerProxy getRandomActiveProxy();

	CrawlerProxy load( int id );

	CrawlerProxy update( CrawlerProxy crawlerProxy );

	CrawlerProxyStatus getProxyStatusById( int id );

}
