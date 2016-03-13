package com.axisdesktop.crawler.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;

import com.axisdesktop.crawler.Parser;
import com.axisdesktop.crawler.impl.DorogaPoiParser;

public class DorogaLinksParserTest {
	public static Parser parser;

	@BeforeClass
	public static void setup() throws IOException {
		InputStream is = new ClassPathResource( "/doroga/sitemap-poi.xml" ).getInputStream();
		BufferedReader br = new BufferedReader( new InputStreamReader( is, StandardCharsets.UTF_8 ) );

		StringBuilder buf = new StringBuilder();
		String line;

		while( ( line = br.readLine() ) != null ) {
			buf.append( line );
		}

		parser = new DorogaPoiParser( buf.toString() );
	}

	@AfterClass
	public static void tearDown() {
		parser = null;
	}

	@Test
	public void testCategoryLinks() {
		System.out.println( "categoryLinks: " + parser.categoryLinks().size() );
	}

	@Test
	public void testItemLinks() {
		System.out.println( "itemLinks: " + parser.itemLinks().size() );
	}

}
