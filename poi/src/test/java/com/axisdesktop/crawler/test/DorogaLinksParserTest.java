package com.axisdesktop.crawler.test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;

import com.axisdesktop.crawler.parser.Parser;
import com.axisdesktop.crawler.parser.impl.DorogaParser;

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

		parser = new DorogaParser( buf.toString() );
	}

	@AfterClass
	public static void tearDown() {
		parser = null;
	}

	@Test
	public void testCategoryLinks() {
		assertThat( "Not all category links parsed", parser.categoryLinks().size(), equalTo( 683 ) );
	}

	@Test
	public void testItemLinks() {
		assertThat( "Not all item links parsed", parser.itemLinks().size(), equalTo( 2064 ) );
	}

}
