// package com.axisdesktop.crawler.test;
//
// import java.io.IOException;
// import java.net.HttpURLConnection;
// import java.net.Proxy;
//
// import org.junit.AfterClass;
// import org.junit.BeforeClass;
// import org.junit.Test;
// import org.junit.runner.RunWith;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.test.context.ContextConfiguration;
// import org.springframework.test.context.TestPropertySource;
// import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
// import com.axisdesktop.crawler.base.Crawler;
// import com.axisdesktop.crawler.entity.ProviderUrl;
// import com.axisdesktop.crawler.impl.DorogaUaCrawler;
// import com.axisdesktop.crawler.service.ProxyService;
// import com.axisdesktop.poi.config.AppConf;
// import com.axisdesktop.poi.config.PersistenceConf;
//
// @RunWith( SpringJUnit4ClassRunner.class )
// @ContextConfiguration( classes = { AppConf.class, PersistenceConf.class } )
// @TestPropertySource( properties = { "crawler.file.store.path = C:/Temp/crawler" } )
// public class DorogaCrawlerTest {
// static Crawler wc;
// static String imgUrl =
// "http://www.doroga.ua/Handlers/ContentImageHandler.ashx?CatalogPOIContentImageID=28546&Size=Big";
//
// // @Autowired
// // ProviderService procService;
// @Autowired
// ProxyService proxyService;
// // @Autowired
// // Environment env;
//
// @BeforeClass
// public static void setUpBeforeClass() throws Exception {
// wc = new DorogaUaCrawler();
// }
//
// @AfterClass
// public static void tearDownAfterClass() throws Exception {
// }
//
// @Test
// public void testGetConnection() throws IOException {
// ProviderUrl iu = new ProviderUrl( 1, imgUrl, 4, 4 );
//
// Proxy proxy = proxyService.getRandomActiveProxy();
//
// HttpURLConnection uc = wc.getConnection( proxy, iu );
// System.err.println( uc.getContentType() );
// System.err.println( uc.getHeaderFields() );
// // System.err.println( uc.getContent().toString() );
// }
//
// }
