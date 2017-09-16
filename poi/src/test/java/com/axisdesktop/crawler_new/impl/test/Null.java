package com.axisdesktop.crawler_new.impl.test;

import static org.junit.Assert.fail;

import java.util.HashMap;
import java.util.Map;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class Null {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void test() {
		Map<String, Object> a = new HashMap<>();
		a.put( "test", null );
		System.out.println( a );
		// ok( "Not yet implemented" );
	}

}
