package com.axisdesktop.crawler_new.base;

import java.util.HashMap;
import java.util.HashSet;

public class ParsedData {
	private HashSet<Link> links = new HashSet<>();
	private HashMap<String, Object> data = new HashMap<>();

	public HashSet<Link> getLinks() {
		return links;
	}

	public void setLinks( HashSet<Link> links ) {
		this.links = links;
	}

	public HashMap<String, Object> getData() {
		return data;
	}

	public void setData( HashMap<String, Object> data ) {
		this.data = data;
	}

}
