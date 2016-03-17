package com.axisdesktop.poi.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan( { "com.axisdesktop.poi.service", "com.axisdesktop.crawler.service", "com.axisdesktop.poi.repository",
		"com.axisdesktop.crawler.repository", "com.axisdesktop.poi.service.impl",
		"com.axisdesktop.crawler.service.impl" } )
@PropertySource( "classpath:application.properties" )
public class StandaloneConfig {

}
