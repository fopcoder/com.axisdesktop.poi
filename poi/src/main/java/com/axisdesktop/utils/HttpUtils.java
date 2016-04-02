package com.axisdesktop.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.Proxy;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class HttpUtils {

	public static HttpURLConnection getConnection( String link ) throws IOException {
		return HttpUtils.getConnection( link, null, null );
	}

	public static HttpURLConnection getConnection( String link, Proxy proxy ) throws IOException {
		return HttpUtils.getConnection( link, proxy, null );
	}

	public static HttpURLConnection getConnection( String link, Proxy proxy, Map<String, String> properties )
			throws IOException, IllegalArgumentException {
		if( link == null ) throw new IllegalArgumentException( "url is null" );
	
		if( properties == null ) {
			properties = new HashMap<>();
		}
	
		URL url = new URL( link );
		HttpURLConnection uc;
	
		if( proxy != null ) {
			uc = (HttpURLConnection)url.openConnection( proxy );
		}
		else {
			uc = (HttpURLConnection)url.openConnection();
		}
	
		if( properties.containsKey( "user-agent" ) ) {
			uc.setRequestProperty( "AppUserEntity-Agent", properties.get( "user-agent" ) );
		}
	
		if( properties.containsKey( "referer" ) ) {
			uc.setRequestProperty( "Referer", properties.get( "referer" ) );
		}
	
		if( properties.containsKey( "timeout" ) ) {
			uc.setConnectTimeout( Integer.valueOf( properties.get( "timeout" ) ) );
		}
		else {
			uc.setConnectTimeout( 10_000 );
		}
	
		if( properties.containsKey( "read-timeout" ) ) {
			uc.setReadTimeout( Integer.valueOf( properties.get( "read-timeout" ) ) );
		}
	
		return uc;
	}

	public static String getTextFromConnection( HttpURLConnection uc ) {
		String text = null;
	
		try( BufferedReader br = new BufferedReader( new InputStreamReader( uc.getInputStream() ) ) ) {
			StringBuilder sb = new StringBuilder();
	
			String str;
			while( ( str = br.readLine() ) != null ) {
				sb.append( str );
			}
	
			text = sb.toString();
	
		}
		catch( IOException e ) { /* ignore */ }
	
		return text;
	}

}
