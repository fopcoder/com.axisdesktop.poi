package com.axisdesktop.crawler.console;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.Environment;

import com.axisdesktop.crawler.base.Crawler;
import com.axisdesktop.crawler.impl.DorogaUaCrawler;
import com.axisdesktop.crawler.service.ProviderService;
import com.axisdesktop.crawler.service.ProxyService;
import com.axisdesktop.poi.config.AppConf;
import com.axisdesktop.poi.config.PersistenceConf;

public class DorogaUaMain {

	private static final Logger logger = LoggerFactory.getLogger( DorogaUaMain.class );

	public static void main( String[] args ) throws IOException {

		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		ctx.register( AppConf.class );
		ctx.register( PersistenceConf.class );
		// ctx.register( SecurityConf.class );
		// ctx.register( SocialConf.class );

		ctx.refresh();

		ProviderService providerService = ctx.getBean( ProviderService.class );
		ProxyService proxyService = ctx.getBean( ProxyService.class );
		Environment env = ctx.getBean( Environment.class );

		// DorogaUaCrawler crawler = new DorogaUaCrawler( proxyService, providerService, env );
		Crawler crawler = new DorogaUaCrawler( proxyService, providerService, env );
		// Crawler crawler = ctx.getBean( DorogaUaCrawler.class );
		crawler.run();

		ctx.close();
		logger.warn( "==>" );

	}

}
