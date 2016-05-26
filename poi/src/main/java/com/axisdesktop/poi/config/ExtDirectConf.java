package com.axisdesktop.poi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan( { "ch.ralscha.extdirectspring", "com.axisdesktop.crawler.service" } )
public class ExtDirectConf {
	@Bean
	public ch.ralscha.extdirectspring.controller.Configuration configuration() {
		ch.ralscha.extdirectspring.controller.Configuration config = new ch.ralscha.extdirectspring.controller.Configuration();
		// config.setMaxRetries( 0 );
		// config.setApiNs( "Ext" );
		config.setActionNs( "Crawler" );
		config.setSendExceptionMessage( true );
		return config;
	}
}
