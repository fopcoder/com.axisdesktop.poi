package com.axisdesktop.crawler.base;

import com.axisdesktop.crawler.service.ProviderService;
import com.axisdesktop.crawler.service.ProxyService;

public abstract class WebCrawler implements Crawler {
	private ProxyService proxyService;
	private ProviderService providerService;

	public WebCrawler() {
	}

	public WebCrawler( ProxyService proxyService, ProviderService providerService ) {
		this.proxyService = proxyService;
		this.providerService = providerService;
	}

	public String getUserAgent() {
		return "Mozilla/5.0 (Windows NT 6.3; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.87 Safari/537.36";
	}

	public String getReferer() {
		return "http://google.com/";
	}

	@Override
	public String getTextContent( String url ) {
		// TODO Auto-generated method stub
		return null;
	}

	public ProxyService getProxyService() {
		return proxyService;
	}

	public void setProxyService( ProxyService proxyService ) {
		this.proxyService = proxyService;
	}

	public ProviderService getProviderService() {
		return providerService;
	}

	public void setProviderService( ProviderService providerService ) {
		this.providerService = providerService;
	}

}
