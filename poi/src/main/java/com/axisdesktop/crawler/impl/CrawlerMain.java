package com.axisdesktop.crawler.impl;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.axisdesktop.crawler.service.ProxyService;
import com.axisdesktop.poi.config.AppConfig;
import com.axisdesktop.poi.config.PersistenceConfig;

public class CrawlerMain {

	public static void main( String[] args ) {

		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		ctx.register( AppConfig.class );
		ctx.register( PersistenceConfig.class );
		ctx.refresh();

		try {
			// ProxyService myService = ctx.getBean( ProxyService.class );
			// DorogaCrawler crawler = new DorogaCrawler();
			// crawler.test();
			// Proxy proxy = ctx.getBean( Proxy.class );
			ProxyService myService = ctx.getBean( ProxyService.class );
			DorogaCrawler crawler = new DorogaCrawler( myService );
			crawler.test();
			// System.out.println( myService.findAll() );
		}
		catch( Exception e ) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ctx.close();
		System.out.println( "==>" );
	}

}
