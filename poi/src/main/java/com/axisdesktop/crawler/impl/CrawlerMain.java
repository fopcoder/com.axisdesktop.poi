package com.axisdesktop.crawler.impl;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.axisdesktop.crawler.base.Crawler;
import com.axisdesktop.poi.config.AppConf;
import com.axisdesktop.poi.config.PersistenceConf;
import com.axisdesktop.poi.config.SecurityConf;
import com.axisdesktop.poi.config.SocialConf;

public class CrawlerMain {

	private static final Logger logger = LoggerFactory.getLogger( CrawlerMain.class );

	public static void main( String[] args ) throws IOException {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		ctx.register( AppConf.class );
		ctx.register( PersistenceConf.class );
		ctx.register( SecurityConf.class );
		ctx.register( SocialConf.class );

		ctx.refresh();

		// ProviderService providerService = ctx.getBean( ProviderService.class );
		// ProxyService proxyService = ctx.getBean( ProxyService.class );

		// DorogaCrawler crawler = new DorogaCrawler( proxyService, providerService );
		// DorogaCrawler crawler = new DorogaCrawler();
		Crawler crawler = ctx.getBean( Crawler.class );
		// crawler.run();

		ctx.close();
		logger.warn( "==>" );

	}

}
