package com.axisdesktop.utils;

public class HttpUtils {
	public static String mime2ext( String mime ) {
		String ext = null;

		if( mime == null ) {}
		else if( mime.contains( "jpeg" ) ) {
			ext = "jpg";
		}
		else if( mime.contains( "png" ) ) {
			ext = "png";
		}
		else if( mime.contains( "gif" ) ) {
			ext = "gif";
		}
		else if( mime.contains( "gif" ) ) {
			ext = "jpg";
		}

		return ext;
	}

	public static String mime2ext( String mime, String def ) {
		String ext = HttpUtils.mime2ext( mime );
		if( ext == null ) ext = def;

		return ext;
	}
}
