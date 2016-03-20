package com.axisdesktop.crawler.base;

import java.util.List;
import java.util.Set;

public abstract class Parser {
	// page meta
	public String title() {
		System.err.println( "[INFO] title() unimplemented" );
		return null;
	}

	// page name
	public String header() {
		System.err.println( "[INFO] header() unimplemented" );
		return null;
	}

	public String shortDescription() {
		System.err.println( "[INFO] shortDescription() unimplemented" );
		return null;
	}

	public String fullDescription() {
		System.err.println( "[INFO] fullDescription() unimplemented" );
		return null;
	}

	public Location location() {
		System.err.println( "[INFO] location() unimplemented" );
		return null;
	}

	public String contacts() {
		System.err.println( "[INFO] contacts() unimplemented" );
		return null;
	}

	public String contactsLink() {
		System.err.println( "[INFO] contactsLink() unimplemented" );
		return null;
	}

	public int status() {
		System.err.println( "[INFO] status() unimplemented" );
		return 0;
	}

	public String statusText() {
		System.err.println( "[INFO] statusText() unimplemented" );
		return null;
	}

	public double rating() {
		System.err.println( "[INFO] rating() unimplemented" );
		return 0;
	}

	public List<Image> images() {
		System.err.println( "[INFO] images() unimplemented" );
		return null;
	}

	public List<Comment> comments() {
		System.err.println( "[INFO] comments() unimplemented" );
		return null;
	}

	public List<String> tags() {
		System.err.println( "[INFO] tags() unimplemented" );
		return null;
	}

	public Set<String> categoryLinks() {
		System.err.println( "[INFO] categoryLinks() unimplemented" );
		return null;
	}

	public Set<String> itemLinks() {
		System.err.println( "[INFO] itemLinks() unimplemented" );
		return null;
	}

}
