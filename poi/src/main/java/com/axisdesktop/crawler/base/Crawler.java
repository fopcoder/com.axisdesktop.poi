package com.axisdesktop.crawler.base;

public interface Crawler extends Runnable {
	String getTextContent( String path );
}
