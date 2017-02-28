package com.axisdesktop.crawler.test;

import org.junit.Test;

public class Escape {

	// @BeforeClass
	// public static void setUpBeforeClass() throws Exception {
	// }
	//
	// @AfterClass
	// public static void tearDownAfterClass() throws Exception {
	// }

	@Test
	public void test() {
		String str = "jhkjhkjh 8\"\\";

		StringBuilder sb = new StringBuilder();
		sb.append( '"' );

		for( int pos = 0; pos < str.length(); pos++ ) {
			char ch = str.charAt( pos );
			if( ch == '"' || ch == '\\' ) {
				sb.append( '\\' );
			}
			sb.append( ch );
		}
		sb.append( '"' );

		System.out.println( str );
		System.out.println( sb.toString() );
		// str = str.replaceAll( "\\", "\\\\" );// .replaceAll( "\"", "\\\\\"" );
		// System.out.println( str );
		// System.out.println( str );
	}

}
