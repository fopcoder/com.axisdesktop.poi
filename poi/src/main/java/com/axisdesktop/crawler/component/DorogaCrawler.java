package com.axisdesktop.crawler.component;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.Proxy;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.axisdesktop.crawler.base.Parser;
import com.axisdesktop.crawler.base.WebCrawler;
import com.axisdesktop.crawler.base.WebWorker;
import com.axisdesktop.crawler.base.Worker;
import com.axisdesktop.crawler.entity.ProviderUrl;
import com.axisdesktop.crawler.impl.DorogaPoiParser;
import com.axisdesktop.crawler.service.ProviderService;
import com.axisdesktop.crawler.service.ProxyService;
import com.axisdesktop.utils.Utils;

@Component
public class DorogaCrawler extends WebCrawler {
	private static final Logger logger = LoggerFactory.getLogger( DorogaCrawler.class );
	private int providerId = 1;

	public DorogaCrawler() {
	}

	public DorogaCrawler( ProxyService proxyService, ProviderService providerService ) {
		super( proxyService, providerService );
	}

	@Override
	public void run() {
		// + get feed uris
		// + get connection
		// + -- update proxy
		// + fetch feed uri
		// + -- update feed
		// + parse feed uri
		// + create workers
		// parse pages
		// save page data

		getAndUpdateFeedUrls();

		ExecutorService exec = Executors.newFixedThreadPool( 10 );
		// getExecutor().execute( new WorkerImpl( getStartUri(), this ) );
		// getExecutor().awaitTermination( 2, TimeUnit.MINUTES );
		// getExecutor().shutdown();

		List<ProviderUrl> updateList = this.getProviderService().findUrlForUpdate( providerId );
		for( ProviderUrl updateUrl : updateList ) {
			Worker worker = new WebWorker( this, updateUrl.getUrl() );

		}

	}

	private void getAndUpdateFeedUrls() {
		List<ProviderUrl> provFeedUrls = this.getProviderService().findActiveFeedUrl( providerId );

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

				feedUrl.setFetched( Calendar.getInstance() );
				feedUrl.setLog( null );
				feedUrl.setStatusId( 1 );
				feedUrl.setTries( 0 );

				this.getProviderService().updateUrl( feedUrl );

				Parser parser = new DorogaPoiParser( this.getTextContent( uc ) );

				for( String s : parser.itemLinks() ) {
					if( !this.getProviderService().isUrlExist( providerId, s ) ) {
						ProviderUrl pu = new ProviderUrl();
						pu.setStatusId( 4 );
						pu.setUrl( s );
						pu.setTypeId( 3 );
						pu.setProviderId( providerId );

						this.getProviderService().createUrl( pu );
					}
				}

			}
			catch( IllegalArgumentException | IOException e ) {
				feedUrl.setFetched( Calendar.getInstance() );
				feedUrl.setLog( e.getMessage() );
				feedUrl.setStatusId( 3 );
				feedUrl.setTries( feedUrl.getTries() + 1 );

				this.getProviderService().updateUrl( feedUrl );
			}

			try {
				Thread.sleep( 2_000 );
			}
			catch( Exception e ) { /* ignore */ }
		}
	}

}
