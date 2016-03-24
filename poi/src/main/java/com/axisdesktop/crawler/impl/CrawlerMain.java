package com.axisdesktop.crawler.impl;

import java.io.IOException;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.axisdesktop.crawler.service.ProviderService;
import com.axisdesktop.crawler.service.ProxyService;
import com.axisdesktop.crawler.service.impl.ProviderServiceImpl;
import com.axisdesktop.crawler.service.impl.ProxyServiceImpl;
import com.axisdesktop.poi.config.AppConfig;
import com.axisdesktop.poi.config.PersistenceConfig;

public class CrawlerMain {

	public static void main( String[] args ) throws IOException {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		ctx.register( AppConfig.class );
		ctx.register( PersistenceConfig.class );
		ctx.refresh();

		ProviderService providerService = ctx.getBean( ProviderServiceImpl.class );
		ProxyService proxyService = ctx.getBean( ProxyServiceImpl.class );

		DorogaCrawler crawler = new DorogaCrawler( proxyService, providerService );
		crawler.run();

		ctx.close();
		System.out.println( "==>" );
	}

}
