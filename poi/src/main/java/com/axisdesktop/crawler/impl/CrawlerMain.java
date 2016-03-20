package com.axisdesktop.crawler.impl;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.axisdesktop.crawler.entity.Proxy;
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

		DorogaCrawler crawler = new DorogaCrawler( providerService, proxyService );
		crawler.run();

		ctx.close();
		System.out.println( "==>" );
	}

}
