package com.axisdesktop.crawler.base;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import com.axisdesktop.crawler.entity.CrawlerProxy;
import com.axisdesktop.crawler.service.ProviderService;
import com.axisdesktop.crawler.service.ProxyService;

public abstract class WebCrawler implements Crawler {
	private ProxyService proxyService;
	private ProviderService providerService;

	public String getUserAgent() {
		return "Mozilla/5.0 (Windows NT 6.3; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.87 Safari/537.36";
	}

	public String getReferer() {
		return "http://google.com/";
	}

	public Proxy getProxy( CrawlerProxy proxy ) throws IOException {
		return this.getProxy( proxy.getHost(), proxy.getPort(), proxy.getUser(), proxy.getPassword() );
	}

	public Proxy getProxy( String host, int port, String user, String pass ) throws IOException {
		if( host == null ) throw new IllegalArgumentException( "host is null" );
		if( port == 0 ) throw new IllegalArgumentException( "port == 0" );

		Proxy proxy = new Proxy( Type.HTTP, new InetSocketAddress( host, port ) );

		HttpURLConnection uc = this.getConnection( "http://google.com/", proxy );

		if( uc.getResponseCode() != 200 ) {
			throw new IllegalStateException( String.format( "%d\n%s", uc.getResponseCode(), uc.getResponseMessage() ) );
		}

		return proxy;
	}

	public HttpURLConnection getConnection( String link ) throws IOException {
		return getConnection( link, null, null );
	}

	public HttpURLConnection getConnection( String link, Proxy proxy ) throws IOException {
		return getConnection( link, proxy, null );
	}

	public HttpURLConnection getConnection( String link, Proxy proxy, Map<String, String> properties )
			throws IOException {
		if( link == null ) throw new IllegalArgumentException( "url is null" );

		if( properties == null ) {
			properties = new HashMap<>();
		}

		URL url = new URL( link );
		HttpURLConnection uc;

		if( proxy != null ) {
			uc = (HttpURLConnection)url.openConnection( proxy );
		}
		else {
			uc = (HttpURLConnection)url.openConnection();
		}

		if( properties.containsKey( "user-agent" ) ) {
			uc.setRequestProperty( "User-Agent", properties.get( "user-agent" ) );
		}

		if( properties.containsKey( "referer" ) ) {
			uc.setRequestProperty( "Referer", properties.get( "referer" ) );
		}

		if( properties.containsKey( "timeout" ) ) {
			uc.setConnectTimeout( Integer.valueOf( properties.get( "timeout" ) ) );
		}
		else {
			properties.put( "timeout", Integer.toString( 10_000 ) );
		}

		if( properties.containsKey( "read-timeout" ) ) {
			uc.setReadTimeout( Integer.valueOf( properties.get( "read-timeout" ) ) );
		}

		return uc;
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
