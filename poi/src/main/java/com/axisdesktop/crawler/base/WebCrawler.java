package com.axisdesktop.crawler.base;

import org.springframework.beans.factory.annotation.Autowired;

import com.axisdesktop.crawler.service.ProviderService;
import com.axisdesktop.crawler.service.ProxyService;

public abstract class WebCrawler implements Crawler {
	@Autowired
	private ProxyService proxyService;

	@Autowired
	private ProviderService providerService;

	public WebCrawler() {
	}

	public WebCrawler( ProxyService proxyService, ProviderService providerService ) {
		this.proxyService = proxyService;
		this.providerService = providerService;
	}

	@Override
	public String getUserAgent() {
		return "Mozilla/5.0 (Windows NT 6.3; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.87 Safari/537.36";
	}

	@Override
	public String getReferer() {
		return "http://google.com/";
	}

	@Override
	public ProxyService getProxyService() {
		return proxyService;
	}

	@Override
	public ProviderService getProviderService() {
		return providerService;
	}

}
