package com.axisdesktop.utils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.Proxy;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Utils {
	public static String calendarToString( Calendar cal ) {
		if( cal == null ) return "";

		String str = "";

		SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd hh:mm:ss" );
		str = sdf.format( cal.getTime() );

		return str;
	}

	public static HttpURLConnection getConnection( String link ) throws IOException {
		return getConnection( link, null, null );
	}

	public static HttpURLConnection getConnection( String link, Proxy proxy ) throws IOException {
		return getConnection( link, proxy, null );
	}

	public static HttpURLConnection getConnection( String link, Proxy proxy, Map<String, String> properties )
			throws IOException {
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
			uc.setRequestProperty( "User-Agent", properties.get( "user-agent" ) );
		}

		if( properties.containsKey( "referer" ) ) {
			uc.setRequestProperty( "Referer", properties.get( "referer" ) );
		}

		if( properties.containsKey( "timeout" ) ) {
			uc.setConnectTimeout( Integer.valueOf( properties.get( "timeout" ) ) );
		}
		else {
			properties.put( "timeout", Integer.toString( 10_000 ) );
		}

		if( properties.containsKey( "read-timeout" ) ) {
			uc.setReadTimeout( Integer.valueOf( properties.get( "read-timeout" ) ) );
		}

		return uc;
	}
}
