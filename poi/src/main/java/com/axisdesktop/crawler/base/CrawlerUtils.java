package com.axisdesktop.crawler.base;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;
import java.net.URLEncoder;
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
			HttpURLConnection uc = HttpUtils.getConnection( providerUrl.getUrl(), proxy, connProps );

			if( uc.getResponseCode() != 200 ) {
				throw new IllegalStateException(
						String.format( "%d\n%s", uc.getResponseCode(), uc.getResponseMessage() ) );
			}

			text = HttpUtils.getTextFromConnection( uc );

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

	public static InputStream urlInputStream( Crawler crawler, Proxy proxy, ProviderUrl providerUrl )
			throws IOException {

		Map<String, Object> params = providerUrl.getParams();

		if( params.containsKey( "method" ) && params.get( "method" ).toString().equalsIgnoreCase( "post" ) ) {
			StringBuilder postData = new StringBuilder();

			for( Map.Entry<String, Object> param : params.entrySet() ) {
				if( postData.length() != 0 ) postData.append( "&" );

				postData.append( URLEncoder.encode( param.getKey(), "UTF-8" ) );
				postData.append( '=' );
				postData.append( URLEncoder.encode( String.valueOf( param.getValue() ), "UTF-8" ) );
			}

			byte[] postDataBytes = postData.toString().getBytes( "UTF-8" );

			URL url = new URL( "http://example.net/new-message.php" );
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			conn.setRequestMethod( "POST" );
			conn.setRequestProperty( "Content-Type", "application/x-www-form-urlencoded" );
			conn.setRequestProperty( "Content-Length", String.valueOf( postDataBytes.length ) );
			conn.setDoOutput( true );
			conn.getOutputStream().write( postDataBytes );

			return conn.getInputStream();

		}
		else {

		}

		return null;
	}
}
