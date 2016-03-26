package com.axisdesktop.crawler.base;

import java.net.HttpURLConnection;

import com.axisdesktop.crawler.service.ProviderService;
import com.axisdesktop.crawler.service.ProxyService;

public interface Crawler extends Runnable {
	String getTextContent( HttpURLConnection con );

	ProxyService getProxyService();

	ProviderService getProviderService();

	String getReferer();

	String getUserAgent();
}
