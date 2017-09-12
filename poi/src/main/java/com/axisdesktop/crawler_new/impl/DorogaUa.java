package com.axisdesktop.crawler_new.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.axisdesktop.crawler.entity.Provider;
import com.axisdesktop.crawler.entity.ProviderDataType;
import com.axisdesktop.crawler.entity.ProviderStatus;
import com.axisdesktop.crawler.entity.ProviderUrl;
import com.axisdesktop.crawler.entity.ProviderUrlStatus;

public class DorogaUa {

	public static void main( String[] args ) {
		SessionFactory sf = new Configuration().addPackage( "com.axisdesktop.crawler.entity" )
				.addAnnotatedClass( Provider.class ).addAnnotatedClass( ProviderStatus.class )
				.addAnnotatedClass( ProviderUrl.class ).addAnnotatedClass( ProviderUrlStatus.class )
				.addAnnotatedClass( ProviderDataType.class ).configure().buildSessionFactory();

		Session session = sf.openSession();

		Provider prov = session.get( Provider.class, 1 );
		System.out.println( prov );

		System.out.println( 77777 );

		sf.close();

	}

}
