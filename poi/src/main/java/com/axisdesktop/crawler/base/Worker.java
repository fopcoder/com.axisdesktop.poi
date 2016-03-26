package com.axisdesktop.crawler.base;

public interface Worker extends Runnable {
	Crawler getCrawler();

	String getUrl();
}
