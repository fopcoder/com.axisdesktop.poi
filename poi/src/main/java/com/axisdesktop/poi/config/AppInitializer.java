package com.axisdesktop.poi.config;

import java.util.EnumSet;
import java.util.TimeZone;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[] { AppConf.class, PersistenceConf.class, SocialConf.class, SecurityConf.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[] { MvcConf.class, ExtDirectConf.class };
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

	@Override
	protected Filter[] getServletFilters() {
		return new Filter[] { new DelegatingFilterProxy( "springSecurityFilterChain" ) };
	}

	@Override
	public void onStartup( ServletContext servletContext ) throws ServletException {
		super.onStartup( servletContext );
		TimeZone.setDefault( TimeZone.getTimeZone( "Europe/Kiev" ) );

		// EnumSet<DispatcherType> dispatcherTypes = EnumSet.of( DispatcherType.REQUEST, DispatcherType.FORWARD );
		// FilterRegistration.Dynamic security = servletContext.addFilter( "springSecurityFilterChain",
		// new DelegatingFilterProxy() );
		// security.addMappingForUrlPatterns( dispatcherTypes, true, "/*" );

	}

}
