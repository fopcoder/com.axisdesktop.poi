package com.axisdesktop.poi.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.orm.hibernate4.HibernateExceptionTranslator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories( { "com.axisdesktop.poi.repository", "com.axisdesktop.crawler.repository" } )
public class PersistenceConfig {
	@Autowired
	private Environment environment;

	@Bean
	DataSource dataSource() {
		DataSource ds = null;

		try {
			// ds = (DataSource)new JndiTemplate().lookup( environment.getRequiredProperty( "db.jndi" ) );

			ds = new SimpleDriverDataSource( new org.postgresql.Driver(), "jdbc:postgresql://127.0.0.1/poi", "poi",
					"poi" );

		}
		catch( Exception e /* IllegalStateException | NamingException e */) {
			// TODO log all exceptions
			e.printStackTrace();
		}

		return ds;
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		vendorAdapter.setGenerateDdl( Boolean.FALSE );
		vendorAdapter.setShowSql( Boolean.TRUE );
		vendorAdapter.setDatabasePlatform( "org.hibernate.spatial.dialect.postgis.PostgisDialect" );
		// vendorAdapter.setDatabase( Database.POSTGRESQL );

		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		factory.setJpaVendorAdapter( vendorAdapter );
		factory.setPackagesToScan( "com.axisdesktop.poi.entity", "com.axisdesktop.poi.entity" );
		factory.setDataSource( dataSource() );
		factory.setPersistenceUnitName( "poi-jndi" );

		Properties jpaProperties = new Properties();
		// jpaProperties.put( "hibernate.format_sql", environment.getProperty( "hibernate.format_sql" ) );
		factory.setJpaProperties( jpaProperties );
		factory.afterPropertiesSet();

		return factory;
	}

	@Bean
	public JpaTransactionManager transactionManager() {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory( entityManagerFactory().getObject() );
		transactionManager.setJpaDialect( new HibernateJpaDialect() );
		return transactionManager;
	}

	@Bean
	public HibernateExceptionTranslator hibernateExceptionTranslator() {
		return new HibernateExceptionTranslator();
	}
}
