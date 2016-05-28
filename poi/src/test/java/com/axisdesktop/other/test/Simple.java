package com.axisdesktop.other.test;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class Simple {
	private String d = "10_000";

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void test() {
		System.err.println( Integer.parseInt( d.replaceAll( "_", "" ) ) );
	}

}
