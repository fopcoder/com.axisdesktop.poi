package com.axisdesktop.crawler.base;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.Proxy;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import com.axisdesktop.crawler.entity.ProviderUrl;
import com.axisdesktop.crawler.service.ProviderService;
import com.axisdesktop.crawler.service.ProxyService;

public abstract class WebCrawler implements Crawler {
	@Autowired
	private ProxyService proxyService;
	@Autowired
	private ProviderService providerService;
	@Autowired
	private Environment env;

	private int connectTimeout = 20_000;

	public WebCrawler() {
	}

	public WebCrawler( ProxyService proxyService, ProviderService providerService, Environment env ) {
		this.proxyService = proxyService;
		this.providerService = providerService;
		this.env = env;
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
	public ProxyService getProxyService() {
		return proxyService;
	}

	@Override
	public ProviderService getProviderService() {
		return providerService;
	}

	@Override
	public int getConnectTimeout() {
		return connectTimeout;
	}

	@Override
	public void setConnectTimeout( int timeout ) {
		connectTimeout = timeout;
	}

	@Override
	public HttpURLConnection getConnection( Proxy proxy, ProviderUrl providerUrl ) throws IOException {
		if( proxy == null ) throw new IllegalArgumentException( "proxy is null" );
		if( providerUrl == null ) throw new IllegalArgumentException( "providerUrl is null" );

		URL url = new URL( providerUrl.getUrl() );
		HttpURLConnection conn = (HttpURLConnection)url.openConnection( proxy );

		conn.setRequestProperty( "User-Agent", this.getUserAgent() );
		conn.setRequestProperty( "Referer", this.getReferer() );
		conn.setConnectTimeout( this.getConnectTimeout() );

		Map<String, Object> params = providerUrl.getParams();

		if( params.containsKey( "method" ) && params.get( "method" ).toString().equalsIgnoreCase( "post" ) ) {
			StringBuilder postData = new StringBuilder();

			for( Map.Entry<String, Object> param : params.entrySet() ) {
				if( postData.length() != 0 ) postData.append( "&" );

				postData.append( URLEncoder.encode( param.getKey(), "UTF-8" ) );
				postData.append( '=' );
				postData.append( URLEncoder.encode( String.valueOf( param.getValue() ), "UTF-8" ) );
			}

			byte[] postDataBytes = postData.toString().getBytes( "UTF-8" );

			conn.setRequestMethod( "POST" );
			conn.setDoOutput( true );
			conn.setRequestProperty( "Content-Type", "application/x-www-form-urlencoded" );
			conn.setRequestProperty( "Content-Length", String.valueOf( postDataBytes.length ) );
			conn.getOutputStream().write( postDataBytes );
		}

		return conn;
	}

	@Override
	public String getTextContent( HttpURLConnection conn ) {
		String text = null;

		try( BufferedReader br = new BufferedReader( new InputStreamReader( conn.getInputStream() ) ) ) {
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
	public Environment getEnvironment() {
		return env;
	}

}
