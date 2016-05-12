package com.axisdesktop.crawler.base;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.Proxy;
import java.util.Map;

import com.axisdesktop.crawler.entity.ProviderUrl;
import com.axisdesktop.crawler.service.ProviderService;
import com.axisdesktop.crawler.service.ProxyService;

public interface Crawler extends Runnable {
	ProxyService getProxyService();

	ProviderService getProviderService();

	String getReferer();

	String getUserAgent();

	int getConnectTimeout();

	void setConnectTimeout( int timeout );

	HttpURLConnection getConnection( Proxy proxy, ProviderUrl providerUrl ) throws IOException;

	String getTextContent( HttpURLConnection conn );

	Map<String, String> getConnectionProperties();

	int getProviderId();
}
