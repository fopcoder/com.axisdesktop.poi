package com.axisdesktop.crawler_new.base;

public interface Parser {
	ParsedData parse();

	ParsedData parse( String raw );
}
