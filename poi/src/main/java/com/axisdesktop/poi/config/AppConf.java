package com.axisdesktop.poi.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan( { "com.axisdesktop.crawler.impl", "com.axisdesktop.crawler.component", "com.axisdesktop.user" } )
@PropertySource( "classpath:application.properties" )
public class AppConf {
	// @Bean
	// public PropertySourcesPlaceholderConfigurer propertyPlaceHolderConfigurer() {
	// return new PropertySourcesPlaceholderConfigurer();
	// }
}
