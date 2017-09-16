package com.axisdesktop.crawler_new.base;

public interface Parser {
	ParsedData parse();

	ParsedData parse( String raw );

	Object get( String key );

	Object get( ParsedData data, String key );
}
