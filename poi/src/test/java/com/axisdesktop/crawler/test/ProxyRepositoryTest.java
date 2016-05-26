package com.axisdesktop.crawler.test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.axisdesktop.crawler.repository.ProxyRepository;
import com.axisdesktop.poi.config.AppConf;
import com.axisdesktop.poi.config.PersistenceConf;

@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration( classes = { AppConf.class, PersistenceConf.class } )
@TestPropertySource( properties = { "crawler.file.store.path = C:/Temp/crawler" } )
public class ProxyRepositoryTest {
	@Autowired
	private ProxyRepository proxyRepo;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void testIsExistByHostAndPort() {
		assertThat( "Host and port not exist", this.proxyRepo.isExistByHostAndPort( "195.5.109.243", 8080 ),
				is( true ) );
	}

}
