package com.axisdesktop.poi.test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.axisdesktop.poi.config.AppConf;
import com.axisdesktop.poi.config.PersistenceConf;
import com.axisdesktop.poi.entity.Trip;
import com.axisdesktop.poi.entity.UserPoint;
import com.axisdesktop.poi.entity.UserPoint2Trip;
import com.axisdesktop.poi.helper.DayListRequestBody;
import com.axisdesktop.poi.helper.TripListSpecification;
import com.axisdesktop.poi.service.TripService;

@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration( classes = { AppConf.class, PersistenceConf.class } )
@TestPropertySource( properties = { "crawler.file.store.path = C:/Temp/crawler" } )
public class TripServiceTest {
	@Autowired
	private TripService tripService;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	@Ignore
	public void tripList() {
		Specification<Trip> spec = new TripListSpecification( 5L, null );
		// List<Trip> tr = tripService.findTrip( spec );

		// assertThat( "Number of trips is wron!", tr.size(), is( 2 ) );
	}

	@Test
	@Ignore
	public void tripDayList() {
		// Specification<Trip> spec = new TripListSpecification( 5L, new DayListRequestBody( 2 ) );
		// List<Trip> tr = tripService.findDay( spec );
		//
		// assertThat( "Number of trips is wron!", tr.size(), is( 3 ) );
	}

	@Test
	public void tripPoints() {
		Trip t = tripService.load( 2 );
		List<UserPoint> res = t.getPoint2trip().stream().map( i -> {
			return i.getPoint();
		} ).collect( Collectors.toList() );
		System.err.println( res );
		// t.getPoints().stream().map( s -> {
		// System.out.println( s.getName() );
		// return s.getName();
		// } ).forEach( System.err::println );
	}

}
