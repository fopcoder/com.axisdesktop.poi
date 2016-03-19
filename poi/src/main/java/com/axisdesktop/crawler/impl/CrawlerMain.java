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

		// "178.150.89.31 ";60297
		// "93.72.105.188 ";8090
		// "176.36.141.92 ";15736

		// java.net.Proxy npr = null;
		//
		// URL urlTest = new URL( "http://google.com/" );
		//
		// npr = new java.net.Proxy( java.net.Proxy.Type.HTTP,
		// new InetSocketAddress( "", proxy.getPort() ) );
		//
		// int code = 0;
		// String msg = null;
		//
		// try {
		// HttpURLConnection uc = (HttpURLConnection)urlTest.openConnection( npr );
		// code = uc.getResponseCode();
		// msg = uc.getResponseMessage();
		// }
		//

		ctx.close();
		System.out.println( "==>" );
	}

}
