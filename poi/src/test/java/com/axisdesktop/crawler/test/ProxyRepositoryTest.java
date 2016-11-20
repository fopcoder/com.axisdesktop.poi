// package com.axisdesktop.crawler.test;
//
// import static org.hamcrest.Matchers.is;
// import static org.junit.Assert.assertThat;
//
// import java.util.Calendar;
// import java.util.List;
//
// import org.junit.AfterClass;
// import org.junit.BeforeClass;
// import org.junit.Test;
// import org.junit.runner.RunWith;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.data.domain.PageRequest;
// import org.springframework.test.context.ContextConfiguration;
// import org.springframework.test.context.TestPropertySource;
// import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
// import com.axisdesktop.crawler.entity.CrawlerProxy;
// import com.axisdesktop.crawler.repository.ProxyRepository;
// import com.axisdesktop.poi.config.AppConf;
// import com.axisdesktop.poi.config.PersistenceConf;
//
// @RunWith( SpringJUnit4ClassRunner.class )
// @ContextConfiguration( classes = { AppConf.class, PersistenceConf.class } )
// @TestPropertySource( properties = { "crawler.file.store.path = C:/Temp/crawler" } )
// public class ProxyRepositoryTest {
// @Autowired
// private ProxyRepository proxyRepo;
// private static Calendar dateDiff;
//
// @BeforeClass
// public static void setUpBeforeClass() throws Exception {
// dateDiff = Calendar.getInstance();
// dateDiff.add( Calendar.MINUTE, -15 );
// }
//
// @AfterClass
// public static void tearDownAfterClass() throws Exception {
// }
//
// @Test
// public void testIsExistByHostAndPort() {
// assertThat( "Host and port not exist", this.proxyRepo.isExistByHostAndPort( "195.5.109.243", 8080 ),
// is( true ) );
// }
//
// @Test
// public void testIsExistByHostAndPort1() {
// assertThat( "Host and port exist", this.proxyRepo.isExistByHostAndPort( "123.456.789.012", 8080 ),
// is( false ) );
// }
//
// @Test
// public void testFindRandomActiveProxy() {
// List<CrawlerProxy> pr = this.proxyRepo.findRandomActiveProxy( dateDiff, 20, new PageRequest( 0, 1 ) );
// assertThat( "Active proxy not found", pr.size(), is( 1 ) );
// }
// }
