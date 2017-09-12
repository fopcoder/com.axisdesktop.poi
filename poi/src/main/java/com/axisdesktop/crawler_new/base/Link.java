package com.axisdesktop.crawler_new.base;

public class Link {
	private String protocol;
	private String host;
	private String link;

	public Link( String l ) {
		this.parseLink( l );
	}

	private void parseLink( String l ) {
		this.protocol = "";
		this.link = "";
		this.host = "";
	}

}
