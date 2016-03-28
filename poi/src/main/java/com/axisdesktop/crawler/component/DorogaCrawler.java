package com.axisdesktop.crawler.component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	// private static final Logger logger = LoggerFactory.getLogger( DorogaCrawler.class );

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

		// ExecutorService exec = Executors.newFixedThreadPool( 10 );
		// getExecutor().execute( new WorkerImpl( getStartUri(), this ) );
		// getExecutor().awaitTermination( 2, TimeUnit.MINUTES );
		// getExecutor().shutdown();

		List<ProviderUrl> updateList = this.getProviderService().findUrlForUpdate( providerId );
		for( ProviderUrl updateUrl : updateList ) {
			Worker worker = new DorogaWorker( this, updateUrl );
			worker.run();
			break;
		}

	}

	private void getAndUpdateFeedUrls() {
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
