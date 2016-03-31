package com.axisdesktop.crawler.impl;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.axisdesktop.crawler.base.Crawler;
import com.axisdesktop.crawler.base.CrawlerUtils;
import com.axisdesktop.crawler.base.Worker;
import com.axisdesktop.crawler.entity.ProviderData;
import com.axisdesktop.crawler.entity.ProviderUrl;
import com.axisdesktop.crawler.parser.Parser;
import com.axisdesktop.crawler.parser.impl.DorogaParser;

public class DorogaWorker implements Worker {
	private static final Logger logger = LoggerFactory.getLogger( DorogaWorker.class );

	private Crawler crawler;
	private ProviderUrl providerUrl;

	public DorogaWorker( Crawler crawler, ProviderUrl providerUrl ) {
		this.crawler = crawler;
		this.providerUrl = providerUrl;
	}

	@Override
	public void run() {
		// + get content
		// + parse content
		// + save content
		// parse subcontent
		// save subcontent
		// catch exceptions

		try {
			String text = CrawlerUtils.getCrawlerUrlTextContent( crawler, providerUrl );

			if( text != null ) {
				Parser parser = new DorogaParser( text );

				ProviderData data = new ProviderData.Builder()//
						.urlId( providerUrl.getId() )//
						.header( parser.header() )//
						.shortDescription( parser.shortDescription() )//
						.fullDescription( parser.fullDescription() )//
						.status( parser.status() )//
						.statusText( parser.statusText() )//
						// .contacts( parser.contacts() )//
						.contactsAddress( parser.contactsAddress() )//
						.contactsPhone( parser.contactsPhone() )//
						.contactsLink( parser.contactsLink() )//
						.contactsWorktime( parser.contactsWorktime() )//
						.rating( parser.rating() )//
						.longitude( parser.location().getLongitude() )//
						.latitude( parser.location().getLatitude() )//
						.languageId( "ru" )//
						.build();

				crawler.getProviderService().saveProviderData( data );
			}
		}
		catch( IOException e ) {
			logger.debug( e.getMessage() );
			logger.trace( e.getStackTrace().toString() );
		}

	}

	@Override
	public Crawler getCrawler() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUrl() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUrlContent() {
		return null;
	}
}
