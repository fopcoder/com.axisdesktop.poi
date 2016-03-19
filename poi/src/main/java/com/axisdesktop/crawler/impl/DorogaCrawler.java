package com.axisdesktop.crawler.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.stereotype.Component;

import com.axisdesktop.crawler.Parser;
import com.axisdesktop.crawler.service.ProxyService;

@Component
public class DorogaCrawler {
	private ProxyService proxyService;
	private ExecutorService exec;
	private int providerId = 1;
	// private Provider provider;

	// @Autowired
	public DorogaCrawler( ProxyService proxyService ) {
		this();
		this.proxyService = proxyService;
	}

	public DorogaCrawler() {
		exec = Executors.newFixedThreadPool( 10 );
	}

	public Object getProxy() {
		try {
			Proxy proxy = new Proxy( Proxy.Type.HTTP, new InetSocketAddress( "93.72.105.188  ", 8090 ) );
			URL url;

			url = new URL( "http://axisdesktop.com" );
			HttpURLConnection uc = (HttpURLConnection)url.openConnection( proxy );
			uc.connect();
		}
		catch( Exception e ) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	public HttpURLConnection getConnection( String link, String referer ) throws IOException {
		Proxy proxy = new Proxy( Proxy.Type.HTTP, new InetSocketAddress( "93.72.105.188", 8090 ) );

		URL url = new URL( link );
		HttpURLConnection uc = (HttpURLConnection)url.openConnection( proxy );
		uc.setRequestProperty( "User-Agent",
				"Mozilla/5.0 (Windows NT 6.3; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.87 Safari/537.36" );
		if( referer != null ) {
			uc.setRequestProperty( "Referer", referer );
		}

		uc.setConnectTimeout( 10000 );
		uc.setReadTimeout( 20 * 60_000 );

		return uc;
	}

	public List<String> getFeedUrls() {
		List<String> l = new ArrayList<>( 1 );
		l.add( "http://www.doroga.ua/sitemap_pois.xml" );

		return l;
	}

	public void run() throws IOException, InterruptedException {
		List<String> feedUrls = this.getFeedUrls();

		for( String url : feedUrls ) {
			HttpURLConnection uc = this.getConnection( url, this.getDefaultReferer() );

			if( uc != null ) {
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

	public String getDefaultReferer() {
		return "http://google.com/";
	}
}
