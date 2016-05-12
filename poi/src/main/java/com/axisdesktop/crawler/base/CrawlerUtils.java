package com.axisdesktop.crawler.base;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.Proxy;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.axisdesktop.crawler.entity.ProviderUrl;
import com.axisdesktop.utils.HttpUtils;

public class CrawlerUtils {
	private static final Logger logger = LoggerFactory.getLogger( CrawlerUtils.class );

	public static String getCrawlerUrlTextContent( Crawler crawler, ProviderUrl providerUrl ) throws IOException {
		String text = null;
		Proxy proxy = crawler.getProxyService().getRandomActiveProxy();

		if( proxy == null ) {
			throw new IOException( "active proxy not found!" );
		}

		Map<String, String> connProps = crawler.getConnectionProperties();
		connProps.put( "user-agent", crawler.getUserAgent() );
		connProps.put( "referer", crawler.getReferer() );

		try {
			HttpURLConnection uc = crawler.getConnection( proxy, providerUrl );

			if( uc.getResponseCode() != 200 ) {
				throw new IllegalStateException(
						String.format( "%d\n%s", uc.getResponseCode(), uc.getResponseMessage() ) );
			}

			text = crawler.getTextContent( uc );

			providerUrl.setLog( null );
			if( providerUrl.getTypeId() == 1 ) { // feed
				providerUrl.setStatusId( 1 );
			}
			else {
				providerUrl.setStatusId( 5 );
			}
			providerUrl.setTries( 0 );

			crawler.getProviderService().updateUrl( providerUrl );
		}
		catch( Exception e ) {
			text = null;

			providerUrl.setLog( e.getMessage() );
			providerUrl.setStatusId( 3 );
			providerUrl.setTries( providerUrl.getTries() + 1 );

			crawler.getProviderService().updateUrl( providerUrl );
		}

		return text;
	}

}
