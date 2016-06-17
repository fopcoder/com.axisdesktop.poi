package com.axisdesktop.poi.test;

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
import com.axisdesktop.poi.entity.UserPoint;
import com.axisdesktop.poi.helper.UserPointListRequestBody;
import com.axisdesktop.poi.helper.UserPointListSpecification;
import com.axisdesktop.poi.service.UserPointService;

@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration( classes = { AppConf.class, PersistenceConf.class } )
@TestPropertySource( properties = { "crawler.file.store.path = C:/Temp/crawler" } )
public class UserPointServiceTest {
	@Autowired
	UserPointService upService;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void listUserPoint() {
		UserPointListRequestBody data = new UserPointListRequestBody( 2, 5 );
		List<UserPoint> res = upService.list( new UserPointListSpecification( data ) );
		System.err.println( res );
	}

}
