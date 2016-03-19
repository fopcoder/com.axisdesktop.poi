package com.axisdesktop.crawler.impl;

import java.io.IOException;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.axisdesktop.crawler.entity.Provider;
import com.axisdesktop.crawler.service.ProviderService;
import com.axisdesktop.crawler.service.ProxyService;
import com.axisdesktop.crawler.service.impl.ProviderServiceImpl;
import com.axisdesktop.crawler.service.impl.ProxyServiceImpl;
import com.axisdesktop.poi.config.AppConfig;
import com.axisdesktop.poi.config.PersistenceConfig;

public class CrawlerMain {

	public static void main( String[] args ) throws IOException, InterruptedException {

		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		ctx.register( AppConfig.class );
		ctx.register( PersistenceConfig.class );
		ctx.refresh();

		ProviderService providerService = ctx.getBean( ProviderServiceImpl.class );
		ProxyService proxyService = ctx.getBean( ProxyServiceImpl.class );

		for( Provider p : providerService.findByStatusId( 1 ) ) {
			System.out.println( p );
		}

		System.out.println( proxyService.getRandomActiveProxy() );

		// System.out.println( providerService.findByStatusId( 1 ) );
		// DorogaCrawler crawler = new DorogaCrawler();
		// crawler.run();

		// try {
		// ProxyService myService = ctx.getBean( ProxyService.class );
		// DorogaCrawler crawler = new DorogaCrawler();
		// crawler.test();
		// Proxy proxy = ctx.getBean( Proxy.class );
		// ProxyService myService = ctx.getBean( ProxyService.class );
		// DorogaCrawler crawler = new DorogaCrawler();
		// crawler.getProxy();
		// System.out.println( myService.findAll() );
		// }
		// catch( Exception e ) {
		// TODO Auto-generated catch block
		// e.printStackTrace();
		// }

		ctx.close();
		System.out.println( "==>" );
	}

}
