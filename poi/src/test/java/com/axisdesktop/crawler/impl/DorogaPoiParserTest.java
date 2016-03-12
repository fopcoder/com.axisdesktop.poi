package com.axisdesktop.crawler.impl;

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

public class DorogaPoiParserTest {
	public static Parser parser;

	@BeforeClass
	public static void setup() throws IOException {
		InputStream is = new ClassPathResource( "/doroga/poi_short.html" ).getInputStream();
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
	public void location() {
		System.out.println( "location: " + parser.location() );
	}

	@Test
	public void header() {
		System.out.println( "header: " + parser.header() );
	}

	@Test
	public void contacts() {
		System.out.println( "contacts: " + parser.contacts() );
	}

	@Test
	public void contacts_link() {
		System.out.println( "contact_link: " + parser.contactsLink() );
	}

	@Test
	public void status() {
		System.out.println( "status: " + parser.status() );
	}

	@Test
	public void status_text() {
		System.out.println( "status_text: " + parser.statusText() );
	}

	@Test
	public void rating() {
		System.out.println( "rating: " + parser.rating() );
	}

	@Test
	public void shortDescription() {
		System.out.println( "short_desc: " + parser.shortDescription() );
	}

	@Test
	public void fullDescription() {
		System.out.println( "full_descr: " + parser.fullDescription() );
	}

	@Test
	public void images() {
		System.out.println( "images: " + parser.images() );
	}

	@Test
	public void comments() {
		System.out.println( "comments: " + parser.comments() );
	}

	@Test
	public void tags() {
		System.out.println( "tags: " + parser.tags() );
	}
}
