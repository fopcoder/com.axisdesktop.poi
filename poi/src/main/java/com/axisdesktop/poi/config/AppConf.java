package com.axisdesktop.poi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@ComponentScan( { "com.axisdesktop.crawler.impl", "com.axisdesktop.user" } )
@PropertySource( "classpath:application.properties" )
public class AppConf {
	@Autowired
	private Environment env;

	@Bean( name = "crawlerFileStorePath" )
	public String getCrawlerFileStorePath() {
		return env.getRequiredProperty( "crawler.file.store.path" );
	}

}
