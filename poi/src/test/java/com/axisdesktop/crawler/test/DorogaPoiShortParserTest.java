package com.axisdesktop.crawler.test;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
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

public class DorogaPoiShortParserTest {
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

		parser = new DorogaParser( buf.toString() );
	}

	@AfterClass
	public static void tearDown() {
		parser = null;
	}

	@Test
	public void location() {
		assertThat( "Location.latitude not found", parser.location().getLatitude(), containsString( "48.4424" ) );
		assertThat( "Location.longitude not found", parser.location().getLongitude(), containsString( "22.7190" ) );
	}

	@Test
	public void header() {
		assertThat( "Header not found", parser.header(), containsString( "Российский драматический" ) );
	}

	@Test
	public void contactAddress() {
		assertThat( "Contact address not found", parser.contactsAddress(), containsString( "Мукачево, пл. Мира" ) );
	}

	@Test
	public void contactLink() {
		assertThat( "Contact link not found", parser.contactsLink(), nullValue() );
	}

	@Test
	public void status() {
		assertThat( "Status not found", parser.status(), nullValue() );
	}

	@Test
	public void statusText() {
		assertThat( "Status text not found", parser.statusText(), nullValue() );
	}

	@Test
	public void rating() {
		assertThat( "Rating not found", parser.rating(), equalTo( "7.5" ) );
	}

	@Test
	public void shortDescription() {
		assertThat( "Short description not found", parser.shortDescription(),
				containsString( "памятник архитектуры" ) );
	}

	@Test
	public void fullDescription() {
		assertThat( "Full description not found", parser.fullDescription(),
				containsString( "Закарпатский областной" ) );
	}

	@Test
	public void images() {
		assertThat( "Images not found", parser.images().size(), is( 2 ) );
	}

	@Test
	public void comments() {
		assertThat( "Comments not found", parser.comments().size(), is( 0 ) );
	}

	@Test
	public void tags() {
		assertThat( "Tags not found", parser.tags(), hasItem( containsString( "памятник" ) ) );
	}
}
