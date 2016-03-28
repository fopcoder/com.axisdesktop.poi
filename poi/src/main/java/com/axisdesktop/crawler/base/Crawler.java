package com.axisdesktop.crawler.base;

import java.util.Map;

import com.axisdesktop.crawler.service.ProviderService;
import com.axisdesktop.crawler.service.ProxyService;

public interface Crawler extends Runnable {
	ProxyService getProxyService();

	ProviderService getProviderService();

	String getReferer();

	String getUserAgent();

	Map<String, String> getConnectionProperties();

	int getProviderId();
}
