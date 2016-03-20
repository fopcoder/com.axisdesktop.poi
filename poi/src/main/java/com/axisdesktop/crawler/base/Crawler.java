package com.axisdesktop.crawler.base;

import java.net.HttpURLConnection;
import java.net.Proxy;

public interface Crawler extends Runnable {
	Proxy getProxy();

	HttpURLConnection getConnection();

	String getUserAgent();

	String getReferer();
}
