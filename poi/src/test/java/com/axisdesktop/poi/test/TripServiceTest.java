package com.axisdesktop.poi.test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
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
import com.axisdesktop.poi.helper.TripSpecification;
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
	public void tripList() {
		Specification<Trip> spec = new TripSpecification( 5L, null );
		List<Trip> tr = tripService.findTrip( spec );

		assertThat( "Number of trips is wron!", tr.size(), is( 2 ) );
	}

}
