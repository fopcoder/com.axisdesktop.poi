package com.axisdesktop.crawler.impl;

import java.io.IOException;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.axisdesktop.crawler.base.Crawler;
import com.axisdesktop.crawler.component.DorogaCrawler;
import com.axisdesktop.poi.config.AppConfig;
import com.axisdesktop.poi.config.PersistenceConfig;

public class CrawlerMain {

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
		System.out.println( "==>" );
	}

}
