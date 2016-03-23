package com.axisdesktop.crawler.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
//import java.net.Proxy;
import java.net.URL;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.stereotype.Component;

import com.axisdesktop.crawler.base.Parser;
import com.axisdesktop.crawler.base.WebCrawler;
import com.axisdesktop.crawler.entity.CrawlerProxy;
import com.axisdesktop.crawler.entity.Provider;
import com.axisdesktop.crawler.entity.ProviderUrl;
import com.axisdesktop.crawler.service.ProviderService;
import com.axisdesktop.crawler.service.ProxyService;

@Component
public class DorogaCrawler extends WebCrawler {
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

	@Override
	public void run() {
		// + get feed uris
		// + get connection
		// + -- update proxy
		// + fetch feed uri
		// + -- update feed
		// + parse feed uri
		// create workers
		// parse pages
		// save page data

		List<ProviderUrl> provFeedUrls = this.provService.findActiveFeedUrl( this.provider.getId() );

		Map<String, String> connProps = new HashMap<>();
		connProps.put( "timeout", Integer.toString( 10_000 ) );
		connProps.put( "read-timeout", Integer.toString( 20 * 60_000 ) );

		for( ProviderUrl feedUrl : provFeedUrls ) {
			String uri = feedUrl.getUrl();
			String msg = null;
			Proxy proxy = null;

			int code;

			CrawlerProxy crawlerProxy = this.proxyService.getRandomActiveProxy();
			try {
				proxy = this.getProxy( crawlerProxy );
			}
			catch( IOException e1 ) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			connProps.put( "user-agent", this.getUserAgent() );
			connProps.put( "referer", this.getReferer() );

			HttpURLConnection uc = this.getConnection( uri, proxy, connProps );

			try {

				// HttpURLConnection uc = this.getConnection( uri, this.getReferer() );

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

						for( String s : parser.itemLinks() ) {
							if( !this.provService.isUrlExist( this.provider.getId(), s ) ) {
								ProviderUrl pu = new ProviderUrl();
								pu.setStatusId( 4 );
								pu.setUrl( s );
								pu.setTypeId( 3 );
								pu.setProviderId( this.provider.getId() );

								this.provService.createUrl( pu );
							}
						}

						// System.err.println( parser.categoryLinks() );

					}
					catch( Exception e ) { /* ignore */ }

					feedUrl.setFetched( Calendar.getInstance() );
					feedUrl.setLog( null );
					feedUrl.setStatusId( 1 );
					feedUrl.setTries( 0 );

					this.provService.updateUrl( feedUrl );
				}
				else {
					feedUrl.setStatusId( 3 );
					feedUrl.setLog( String.format( "%s\n%s", code, msg ) );
					feedUrl.setTries( feedUrl.getTries() + 1 );
					feedUrl.setFetched( Calendar.getInstance() );

					this.provService.updateUrl( feedUrl );
				}
			}
			catch( Exception e ) {
				feedUrl.setFetched( Calendar.getInstance() );
				feedUrl.setLog( String.format( "%s\n%s", code, e.toString() ) );
				feedUrl.setStatusId( 3 );
				feedUrl.setTries( feedUrl.getTries() + 1 );

				this.provService.updateUrl( feedUrl );
			}

			try {
				Thread.sleep( 2000 );
			}
			catch( InterruptedException e ) { /* ignore */ }
		}

		List<ProviderUrl> updList = this.provService.findUrlForUpdate( this.provider.getId() );
		for( ProviderUrl provUrl : updList ) {
			String uri = provUrl.getUrl();
			int code = 0;
			String msg = null;

			CrawlerProxy crawlerProxy = this.proxyService.getRandomActiveProxy();
			Proxy proxy = this.getProxy( crawlerProxy );

			try {
				HttpURLConnection uc = this.getConnection( uri, proxy );

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

						System.out.println( parser );

						Thread.sleep( 2_000 );

						// System.err.println( parser.itemLinks() );

						// for( String s : parser.itemLinks() ) {
						// if( !this.provService.isUrlExist( this.provider.getId(), s ) ) {
						// ProviderUrl pu = new ProviderUrl();
						// pu.setStatusId( 4 );
						// pu.setUrl( s );
						// pu.setTypeId( 3 );
						// pu.setProviderId( this.provider.getId() );
						//
						// this.provService.createUrl( pu );
						// }
						// }

						// System.err.println( parser.categoryLinks() );

					}
					catch( Exception e ) { /* ignore */ }

					provUrl.setFetched( Calendar.getInstance() );
					provUrl.setLog( null );
					provUrl.setStatusId( 5 );
					provUrl.setTries( 0 );

					this.provService.updateUrl( provUrl );
				}
				else {
					provUrl.setStatusId( 3 );
					provUrl.setLog( String.format( "%s\n%s", code, msg ) );
					provUrl.setTries( provUrl.getTries() + 1 );
					provUrl.setFetched( Calendar.getInstance() );

					this.provService.updateUrl( provUrl );
				}

			}
			catch( IOException e ) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public Proxy getProxy2() {
		CrawlerProxy crawlerProxy = null;
		java.net.Proxy npr = null;
		URL urlTest = null;

		try {
			urlTest = new URL( "http://google.com/" );
		}
		catch( MalformedURLException e ) { /* ignore */ }

		while( ( crawlerProxy = this.proxyService.getRandomActiveProxy() ) != null ) {
			npr = new java.net.Proxy( java.net.Proxy.Type.HTTP,
					new InetSocketAddress( crawlerProxy.getHost().trim(), crawlerProxy.getPort() ) );

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
				crawlerProxy.setStatusId( 1 );
				crawlerProxy.setLog( null );
				crawlerProxy.setTries( 0 );
				crawlerProxy.setFetched( Calendar.getInstance() );

				this.proxyService.update( crawlerProxy );

				break;
			}
			else {
				npr = null;

				crawlerProxy.setStatusId( 3 );
				crawlerProxy.setLog( String.format( "%s\n%s", code, msg ) );
				crawlerProxy.setTries( crawlerProxy.getTries() + 1 );
				crawlerProxy.setFetched( Calendar.getInstance() );

				this.proxyService.update( crawlerProxy );

				continue;
			}
		}

		return npr;
	}

}
