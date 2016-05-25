package com.axisdesktop.crawler.service;

import java.net.Proxy;
import java.util.List;

import com.axisdesktop.crawler.entity.CrawlerProxy;
import com.axisdesktop.crawler.entity.CrawlerProxyStatus;

import ch.ralscha.extdirectspring.bean.ExtDirectStoreReadRequest;
import ch.ralscha.extdirectspring.bean.ExtDirectStoreResult;

public interface ProxyService {
	List<CrawlerProxy> findAll();

	CrawlerProxy create( CrawlerProxy proxy );

	CrawlerProxy load( int id );

	CrawlerProxy update( CrawlerProxy crawlerProxy );

	CrawlerProxy delete( int id );

	CrawlerProxyStatus getProxyStatusById( int id );

	Proxy getRandomActiveProxy();

	Proxy getRandomActiveProxy( int proxyTimeout, int proxyMaxTries, String proxyTestDomain );

}
