package com.axisdesktop.crawler.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.Proxy;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.axisdesktop.crawler.base.WebCrawler;
import com.axisdesktop.crawler.entity.Provider;
import com.axisdesktop.crawler.entity.ProviderUrl;
import com.axisdesktop.crawler.service.ProviderService;
import com.axisdesktop.crawler.service.ProxyService;
import com.axisdesktop.utils.Utils;

@Component
public class DorogaCrawler extends WebCrawler {
	private static final Logger logger = LoggerFactory.getLogger( DorogaCrawler.class );

	// private ExecutorService exec;
	private Provider provider;

	@Autowired
	public DorogaCrawler( ProxyService proxyService, ProviderService providerService ) {
		super( proxyService, providerService );
		this.provider = providerService.load( 1 );
	}

	public DorogaCrawler() {
		// exec = Executors.newFixedThreadPool( 10 );
	}

	@Override
	public void run() {
		// + get feed uris
		// get connection
		// -- update proxy
		// fetch feed uri
		// -- update feed
		// parse feed uri
		// create workers
		// parse pages
		// save page data

		logger.info( "===================> start" );
		logger.info( "" + this.provider.getId() );

		List<ProviderUrl> provFeedUrls = this.getProviderService().findActiveFeedUrl( this.provider.getId() );

		Map<String, String> connProps = new HashMap<>();
		connProps.put( "read-timeout", Integer.toString( 20 * 60_000 ) );

		for( ProviderUrl feedUrl : provFeedUrls ) {
			Proxy proxy = this.getProxyService().getRandomActiveProxy();

			if( proxy == null ) {
				logger.warn( "active proxy not found!" );
				return;
			}

			connProps.put( "user-agent", this.getUserAgent() );
			connProps.put( "referer", this.getReferer() );

			try {
				HttpURLConnection uc = Utils.getConnection( feedUrl.getUrl(), proxy, connProps );

				if( uc.getResponseCode() != 200 ) {
					throw new IllegalStateException(
							String.format( "%d\n%s", uc.getResponseCode(), uc.getResponseMessage() ) );
				}

				if( uc.getContentType().contains( "text" ) ) {
					try( BufferedReader br = new BufferedReader( new InputStreamReader( uc.getInputStream() ) ) ) {
						StringBuilder sb = new StringBuilder();

						String str;
						while( ( str = br.readLine() ) != null ) {
							sb.append( str );
						}

						logger.info( sb.toString() );

						// Parser parser = new DorogaPoiParser( sb.toString() );
						//
						// for( String s : parser.itemLinks() ) {
						// if( !this.getProviderService().isUrlExist( this.provider.getId(), s ) ) {
						// ProviderUrl pu = new ProviderUrl();
						// pu.setStatusId( 4 );
						// pu.setUrl( s );
						// pu.setTypeId( 3 );
						// pu.setProviderId( this.provider.getId() );
						//
						// this.getProviderService().createUrl( pu );
						// }
						// }

					}
					catch( Exception e ) { /* ignore */ }

					feedUrl.setFetched( Calendar.getInstance() );
					feedUrl.setLog( null );
					feedUrl.setStatusId( 1 );
					feedUrl.setTries( 0 );

					this.getProviderService().updateUrl( feedUrl );
				}

			}
			catch( Exception e ) {
				feedUrl.setFetched( Calendar.getInstance() );
				feedUrl.setLog( e.getMessage() );
				feedUrl.setStatusId( 3 );
				feedUrl.setTries( feedUrl.getTries() + 1 );

				this.getProviderService().updateUrl( feedUrl );
			}

			try {
				Thread.sleep( 2000 );
			}
			catch( InterruptedException e ) { /* ignore */ }
		}

		// List<ProviderUrl> updList = this.getProviderService().findUrlForUpdate( this.provider.getId() );
		// for( ProviderUrl provUrl : updList ) {
		// String uri = provUrl.getUrl();
		// int code = 0;
		// String msg = null;
		//
		// CrawlerProxy crawlerProxy = this.getProxyService().getRandomActiveProxy();
		// Proxy proxy = this.getProxy( crawlerProxy );
		//
		// try {
		// HttpURLConnection uc = this.getConnection( uri, proxy );
		//
		// if( uc == null ) {
		// System.err.println( "no free proxy" );
		// break;
		// }
		//
		// code = uc.getResponseCode();
		// msg = uc.getResponseMessage();
		//
		// if( code == 200 && uc.getContentType().contains( "text" ) ) {
		// try( BufferedReader br = new BufferedReader( new InputStreamReader( uc.getInputStream() ) ) ) {
		// StringBuilder sb = new StringBuilder();
		//
		// String str;
		// while( ( str = br.readLine() ) != null ) {
		// sb.append( str );
		// }
		//
		// Parser parser = new DorogaPoiParser( sb.toString() );
		//
		// System.out.println( parser );
		//
		// Thread.sleep( 2_000 );
		//
		// // System.err.println( parser.itemLinks() );
		//
		// // for( String s : parser.itemLinks() ) {
		// // if( !this.provService.isUrlExist( this.provider.getId(), s ) ) {
		// // ProviderUrl pu = new ProviderUrl();
		// // pu.setStatusId( 4 );
		// // pu.setUrl( s );
		// // pu.setTypeId( 3 );
		// // pu.setProviderId( this.provider.getId() );
		// //
		// // this.provService.createUrl( pu );
		// // }
		// // }
		//
		// // System.err.println( parser.categoryLinks() );
		//
		// }
		// catch( Exception e ) { /* ignore */ }
		//
		// provUrl.setFetched( Calendar.getInstance() );
		// provUrl.setLog( null );
		// provUrl.setStatusId( 5 );
		// provUrl.setTries( 0 );
		//
		// this.provService.updateUrl( provUrl );
		// }
		// else {
		// provUrl.setStatusId( 3 );
		// provUrl.setLog( String.format( "%s\n%s", code, msg ) );
		// provUrl.setTries( provUrl.getTries() + 1 );
		// provUrl.setFetched( Calendar.getInstance() );
		//
		// this.provService.updateUrl( provUrl );
		// }
		//
		// }
		// catch( IOException e ) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// }

	}

}
