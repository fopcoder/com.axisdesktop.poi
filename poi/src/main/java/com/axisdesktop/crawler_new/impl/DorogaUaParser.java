package com.axisdesktop.crawler_new.impl;

import com.axisdesktop.crawler_new.base.ParsedData;
import com.axisdesktop.crawler_new.base.Parser;

public class DorogaUaParser implements Parser {
	private String raw;

	public DorogaUaParser() {
	}

	public DorogaUaParser( String raw ) {
		this.raw = raw;
	}

	@Override
	public ParsedData parse() {
		return this.parse( this.raw );
	}

	@Override
	public ParsedData parse( String raw ) {
		// TODO Auto-generated method stub
		return null;
	}

}
