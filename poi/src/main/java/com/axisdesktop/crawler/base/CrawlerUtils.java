package com.axisdesktop.crawler.base;

import java.net.HttpURLConnection;
import java.net.Proxy;
import java.util.Calendar;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.axisdesktop.crawler.entity.ProviderUrl;
import com.axisdesktop.utils.Utils;

public class CrawlerUtils {
	private static final Logger logger = LoggerFactory.getLogger( CrawlerUtils.class );

	public static String getCrawlerUrlTextContent( Crawler crawler, ProviderUrl providerUrl ) {
		String text = null;
		Proxy proxy = crawler.getProxyService().getRandomActiveProxy();

		if( proxy == null ) {
			logger.warn( "active proxy not found!" );
			return null;
		}

		Map<String, String> connProps = crawler.getConnectionProperties();
		connProps.put( "user-agent", crawler.getUserAgent() );
		connProps.put( "referer", crawler.getReferer() );

		try {
			HttpURLConnection uc = Utils.getConnection( providerUrl.getUrl(), proxy, connProps );

			if( uc.getResponseCode() != 200 ) {
				throw new IllegalStateException(
						String.format( "%d\n%s", uc.getResponseCode(), uc.getResponseMessage() ) );
			}

			text = Utils.getTextFromConnection( uc );

			providerUrl.setFetched( Calendar.getInstance() );
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

			providerUrl.setFetched( Calendar.getInstance() );
			providerUrl.setLog( e.getMessage() );
			providerUrl.setStatusId( 3 );
			providerUrl.setTries( providerUrl.getTries() + 1 );

			crawler.getProviderService().updateUrl( providerUrl );
		}

		return text;
	}
}
