package com.axisdesktop.poi.test;

import java.util.ArrayList;
import java.util.List;

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
import com.axisdesktop.poi.entity.UserPoint;
import com.axisdesktop.poi.entity.UserPoint2Trip;
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
		Trip t = tripService.loadTrip( 2 );

		System.err.println( t.getPoint2trip().size() );

		List<UserPoint> l = new ArrayList<>();

		for( UserPoint2Trip up : t.getPoint2trip() ) {
			l.add( up.getPoint() );
			// System.err.println( up.getPoint().getName() );
		}
		System.err.println( l );
		// t.getPoint2trip().stream().map( s -> {
		// System.err.println( s.getPoint().getName() + " == " + s.getPorder() );
		// return s.getPoint();
		// } );
	}

}
