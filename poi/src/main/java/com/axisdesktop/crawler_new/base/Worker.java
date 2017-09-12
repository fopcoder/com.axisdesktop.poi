package com.axisdesktop.crawler_new.base;

public interface Worker extends Runnable {
	Crawler getCrawler();

	String getUrl();

	String getUrlContent();
}
