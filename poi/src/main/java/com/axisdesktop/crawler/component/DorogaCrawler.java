package com.axisdesktop.crawler.component;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.axisdesktop.crawler.base.CrawlerUtils;
import com.axisdesktop.crawler.base.WebCrawler;
import com.axisdesktop.crawler.base.Worker;
import com.axisdesktop.crawler.entity.ProviderUrl;
import com.axisdesktop.crawler.impl.DorogaWorker;
import com.axisdesktop.crawler.parser.Parser;
import com.axisdesktop.crawler.parser.impl.DorogaParser;
import com.axisdesktop.crawler.service.ProviderService;
import com.axisdesktop.crawler.service.ProxyService;

@Component
public class DorogaCrawler extends WebCrawler {
	private static final Logger logger = LoggerFactory.getLogger( DorogaCrawler.class );

	private final int providerId = 1;
	private Map<String, String> connProps = new HashMap<>();

	{
		connProps.put( "read-timeout", Integer.toString( 20 * 60_000 ) );
	}

	public DorogaCrawler() {
	}

	public DorogaCrawler( ProxyService proxyService, ProviderService providerService ) {
		super( proxyService, providerService );
	}

	@Override
	public void run() {
		try {
			this.getAndUpdateFeedUrls();

			// ExecutorService exec = Executors.newFixedThreadPool( 5 );

			List<ProviderUrl> updateList = this.getProviderService().findUrlForUpdate( providerId );
			for( ProviderUrl updateUrl : updateList ) {
				Worker worker = new DorogaWorker( this, updateUrl );
				// exec.execute( worker );
				worker.run();
				Thread.sleep( 1_000 );
				// break;
			}

			// exec.shutdown();
		}
		catch( Exception e ) {
			e.printStackTrace();
			// logger.error( e.getMessage() );
			// logger.error( e.getStackTrace().toString() );
		}
	}

	private void getAndUpdateFeedUrls() throws IOException {
		List<ProviderUrl> provFeedUrls = this.getProviderService().findActiveFeedUrl( providerId );

		for( ProviderUrl feedUrl : provFeedUrls ) {
			String text = CrawlerUtils.getCrawlerUrlTextContent( this, feedUrl );

			if( text != null ) {
				Parser parser = new DorogaParser( text );

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

				try {
					Thread.sleep( 2_000 );
				}
				catch( Exception e ) { /* ignore */ }
			}
		}
	}

	@Override
	public Map<String, String> getConnectionProperties() {
		return connProps;
	}

	@Override
	public int getProviderId() {
		return providerId;
	}

}
