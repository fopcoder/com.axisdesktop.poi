package com.axisdesktop.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Utils {
	public static String calendarToString( Calendar cal ) {
		if( cal == null ) return "";

		String str = "";

		SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd hh:mm:ss" );
		str = sdf.format( cal.getTime() );

		return str;
	}
}
