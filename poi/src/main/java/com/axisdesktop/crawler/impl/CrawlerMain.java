package com.axisdesktop.crawler.impl;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.axisdesktop.crawler.base.Crawler;
import com.axisdesktop.poi.config.AppConfig;
import com.axisdesktop.poi.config.PersistenceConfig;

public class CrawlerMain {

	private static final Logger logger = LoggerFactory.getLogger( CrawlerMain.class );

	public static void main( String[] args ) throws IOException {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		ctx.register( AppConfig.class );
		ctx.register( PersistenceConfig.class );
		ctx.refresh();

		// ProviderService providerService = ctx.getBean( ProviderService.class );
		// ProxyService proxyService = ctx.getBean( ProxyService.class );

		// DorogaCrawler crawler = new DorogaCrawler( proxyService, providerService );
		// DorogaCrawler crawler = new DorogaCrawler();
		Crawler crawler = ctx.getBean( Crawler.class );
		crawler.run();

		ctx.close();
		logger.warn( "==>" );

	}

}
