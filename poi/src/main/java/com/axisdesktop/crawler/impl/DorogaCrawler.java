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

import com.axisdesktop.crawler.Parser;
import com.axisdesktop.crawler.entity.Provider;
import com.axisdesktop.crawler.entity.ProviderFeedUri;
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

	public void run() throws IOException, InterruptedException {
		// + get feed uris
		// get connection
		// -- update proxy
		// fetch feed uri
		// -- update feed
		// parse feed uri
		// create workers

		List<ProviderFeedUri> provFeeds = this.provService.findActiveFeedUri( this.provider.getId() );

		for( ProviderFeedUri feed : provFeeds ) {
			String uri = feed.getUri();

			HttpURLConnection uc = this.getConnection( uri, this.getReferer() );

			if( false && uc != null ) {
				if( uc.getContentType().contains( "text" ) ) {

					try( BufferedReader br = new BufferedReader( new InputStreamReader( uc.getInputStream() ) ) ) {
						StringBuilder sb = new StringBuilder();

						String str;
						while( ( str = br.readLine() ) != null ) {
							sb.append( str );
						}

						Parser parser = new DorogaPoiParser( sb.toString() );
						System.out.println( parser.itemLinks() );

					}
					catch( Exception e ) { /* ignore */ }
				}
			}

			Thread.sleep( 2000 );
		}
	}

	public HttpURLConnection getConnection( String link, String referer ) {
		HttpURLConnection uc = null;
		java.net.Proxy npr = this.getProxy();

		if( npr == null ) return null;

		URL url = null;

		try {
			url = new URL( link );
			uc = (HttpURLConnection)url.openConnection( npr );
		}
		catch( IOException e ) {
			e.printStackTrace();
		}

		uc.setRequestProperty( "User-Agent", this.getUserAgent() );

		if( referer != null ) {
			uc.setRequestProperty( "Referer", referer );
		}

		uc.setConnectTimeout( 10000 );
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
		catch( MalformedURLException e ) {
			e.printStackTrace();
			return null;
		}

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

			if( code != 200 ) {
				npr = null;

				proxy.setProxyStatus( this.proxyService.getProxyStatusById( 3 ) );
				proxy.setLog( String.format( "%s\n%s", code, msg ) );
				proxy.setTries( proxy.getTries() + 1 );
				proxy.setFetched( Calendar.getInstance() );

				this.proxyService.update( proxy );

				continue;
			}
			else {
				proxy.setProxyStatus( this.proxyService.getProxyStatusById( 1 ) );
				proxy.setLog( null );
				proxy.setTries( 0 );
				proxy.setFetched( Calendar.getInstance() );

				this.proxyService.update( proxy );

				break;
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
