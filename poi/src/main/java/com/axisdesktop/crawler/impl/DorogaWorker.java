package com.axisdesktop.crawler.impl;

import com.axisdesktop.crawler.base.Crawler;
import com.axisdesktop.crawler.base.Parser;
import com.axisdesktop.crawler.base.Worker;

public class DorogaWorker implements Worker {
	private Crawler crawler;
	private String url;

	public DorogaWorker( Crawler crawler, String url ) {
		this.crawler = crawler;
		this.url = url;
	}

	@Override
	public void run() {
		// get content
		// parse content
		// save content
		// parse subcontent
		// save subcontent
		// catch exceptions

		// String html = crawler.getTextContent( this.url );

		// Parser parser = crawler.getParser( html );
		// crawler.setUrlData( parser.getUrlData() );
		// crawler.saveData();
		//
		// crawler.putNewUrl( parser.getNewUrl() );
		//
		// crawler.updateUrl();

		// update url

	}

	@Override
	public Crawler getCrawler() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUrl() {
		// TODO Auto-generated method stub
		return null;
	}
}
