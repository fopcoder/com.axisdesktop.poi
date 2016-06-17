package com.axisdesktop.poi.test;

import org.hibernate.sql.ordering.antlr.GeneratedOrderByFragmentParser;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.axisdesktop.poi.config.AppConf;
import com.axisdesktop.poi.config.PersistenceConf;
import com.axisdesktop.poi.entity.Trip;
import com.axisdesktop.poi.service.TripService;

@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration( classes = { AppConf.class, PersistenceConf.class } )
@TestPropertySource( properties = { "crawler.file.store.path = C:/Temp/crawler" } )
public class TripPointListTest {
	@Autowired
	private TripService tripService;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void tripPoints() {
		Trip t = tripService.loadDay( 2 );

		t.getPoint2trip().stream().forEach( s -> {
			System.err.println( s.getPoint().getName() + " == " + s.getPorder() );
		} );
	}

}
