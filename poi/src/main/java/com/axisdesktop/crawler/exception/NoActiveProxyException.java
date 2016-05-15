package com.axisdesktop.crawler.exception;

@SuppressWarnings( "serial" )
public class NoActiveProxyException extends Exception {
	public NoActiveProxyException() {
		super( "Active proxy not found!" );
	}

	public NoActiveProxyException( String message ) {
		super( message );
	}

	public NoActiveProxyException( String message, Throwable throwable ) {
		super( message, throwable );
	}
}
