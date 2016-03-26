package com.axisdesktop.crawler.base;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

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
	public String getTextContent( HttpURLConnection uc ) {
		String text = null;

		try( BufferedReader br = new BufferedReader( new InputStreamReader( uc.getInputStream() ) ) ) {
			StringBuilder sb = new StringBuilder();

			String str;
			while( ( str = br.readLine() ) != null ) {
				sb.append( str );
			}

			text = sb.toString();

		}
		catch( IOException e ) { /* ignore */ }

		return text;
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
