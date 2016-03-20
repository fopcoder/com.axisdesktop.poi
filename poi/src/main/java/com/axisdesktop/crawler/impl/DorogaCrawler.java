package com.axisdesktop.crawler.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
//import java.net.Proxy;
import java.net.URL;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.stereotype.Component;

import com.axisdesktop.crawler.base.Parser;
import com.axisdesktop.crawler.entity.Provider;
import com.axisdesktop.crawler.entity.ProviderUrl;
import com.axisdesktop.crawler.entity.Proxy;
import com.axisdesktop.crawler.service.ProviderService;
import com.axisdesktop.crawler.service.ProxyService;

@Component
public class DorogaCrawler {
	private ProxyService proxyService;
	private ProviderService provService;
	private ExecutorService exec;
	private Provider provider;

	// @Autowired
	public DorogaCrawler( ProviderService provService, ProxyService proxyService ) {
		this();
		this.proxyService = proxyService;
		this.provService = provService;
		this.provider = provService.load( 1 );
	}

	public DorogaCrawler() {
		exec = Executors.newFixedThreadPool( 10 );
	}

	public void run() {
		// + get feed uris
		// + get connection
		// + -- update proxy
		// + fetch feed uri
		// + -- update feed
		// parse feed uri
		// create workers

		List<ProviderUrl> provFeeds = this.provService.findActiveFeedUri( this.provider.getId() );

		for( ProviderUrl feed : provFeeds ) {
			String uri = feed.getUrl();
			int code = 0;
			String msg = null;

			try {
				HttpURLConnection uc = this.getConnection( uri, this.getReferer() );

				if( uc == null ) {
					System.err.println( "no free proxy" );
					break;
				}

				code = uc.getResponseCode();
				msg = uc.getResponseMessage();

				if( code == 200 && uc.getContentType().contains( "text" ) ) {
					try( BufferedReader br = new BufferedReader( new InputStreamReader( uc.getInputStream() ) ) ) {
						StringBuilder sb = new StringBuilder();

						String str;
						while( ( str = br.readLine() ) != null ) {
							sb.append( str );
						}

						Parser parser = new DorogaPoiParser( sb.toString() );
						System.err.println( parser.itemLinks() );

					}
					catch( Exception e ) { /* ignore */ }

					feed.setFetched( Calendar.getInstance() );
					feed.setLog( null );
					feed.setStatusId( 1 );
					feed.setTries( 0 );

					this.provService.updateUrl( feed );
				}
				else {
					feed.setStatusId( 3 );
					feed.setLog( String.format( "%s\n%s", code, msg ) );
					feed.setTries( feed.getTries() + 1 );
					feed.setFetched( Calendar.getInstance() );

					this.provService.updateUrl( feed );
				}
			}
			catch( Exception e ) {
				feed.setFetched( Calendar.getInstance() );
				feed.setLog( String.format( "%s\n%s", code, e.toString() ) );
				feed.setStatusId( 3 );
				feed.setTries( feed.getTries() + 1 );

				this.provService.updateUrl( feed );
			}

			try {
				Thread.sleep( 2000 );
			}
			catch( InterruptedException e ) { /* ignore */ }
		}
	}

	public HttpURLConnection getConnection( String link, String referer ) throws IOException {
		HttpURLConnection uc = null;
		java.net.Proxy npr = this.getProxy();

		if( npr == null ) return null;

		URL url = null;

		url = new URL( link );
		uc = (HttpURLConnection)url.openConnection( npr );

		uc.setRequestProperty( "User-Agent", this.getUserAgent() );

		if( referer != null ) {
			uc.setRequestProperty( "Referer", referer );
		}

		uc.setConnectTimeout( 10_000 );
		uc.setReadTimeout( 20 * 60_000 );

		return uc;
	}

	public java.net.Proxy getProxy() {
		Proxy proxy = null;
		java.net.Proxy npr = null;
		URL urlTest = null;

		try {
			urlTest = new URL( "http://google.com/" );
		}
		catch( MalformedURLException e ) { /* ignore */ }

		while( ( proxy = this.proxyService.getRandomActiveProxy() ) != null ) {
			npr = new java.net.Proxy( java.net.Proxy.Type.HTTP,
					new InetSocketAddress( proxy.getHost().trim(), proxy.getPort() ) );

			int code = 0;
			String msg = null;

			try {
				HttpURLConnection uc = (HttpURLConnection)urlTest.openConnection( npr );
				uc.setConnectTimeout( 10_000 );

				code = uc.getResponseCode();
				msg = uc.getResponseMessage();
			}
			catch( IOException e ) {
				e.printStackTrace();
				msg = e.toString();
			}

			if( code == 200 ) {
				proxy.setStatusId( 1 );
				proxy.setLog( null );
				proxy.setTries( 0 );
				proxy.setFetched( Calendar.getInstance() );

				this.proxyService.update( proxy );

				break;
			}
			else {
				npr = null;

				proxy.setStatusId( 3 );
				proxy.setLog( String.format( "%s\n%s", code, msg ) );
				proxy.setTries( proxy.getTries() + 1 );
				proxy.setFetched( Calendar.getInstance() );

				this.proxyService.update( proxy );

				continue;
			}
		}

		return npr;
	}

	public String getReferer() {
		return "http://google.com/";
	}

	public String getUserAgent() {
		return "Mozilla/5.0 (Windows NT 6.3; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.87 Safari/537.36";
	}
}
