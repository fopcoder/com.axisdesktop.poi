package com.axisdesktop.crawler_new.impl.test;

import static org.junit.Assert.fail;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.axisdesktop.crawler_new.base.Parser;
import com.axisdesktop.crawler_new.impl.DorogaUaParser;

public class DorogaUaParserTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void testParse() {
		Parser p = new DorogaUaParser( "" );
		p.parse();
		fail( "Not yet implemented" );
	}

	@Test
	public void testParseString() {
		Parser p = new DorogaUaParser();
		p.parse( "" );
		fail( "Not yet implemented" );
	}

}
