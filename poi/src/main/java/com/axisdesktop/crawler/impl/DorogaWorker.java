package com.axisdesktop.crawler.impl;

import java.io.IOException;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.axisdesktop.crawler.base.Crawler;
import com.axisdesktop.crawler.base.CrawlerUtils;
import com.axisdesktop.crawler.base.Worker;
import com.axisdesktop.crawler.entity.ProviderData;
import com.axisdesktop.crawler.entity.ProviderUrl;
import com.axisdesktop.crawler.parser.Image;
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
		try {
			String text = CrawlerUtils.getCrawlerUrlTextContent( crawler, providerUrl );

			if( text != null ) {
				Parser parser = new DorogaParser( text );

				for( Image i : parser.images() ) {
					if( !crawler.getProviderService().isUrlExist( providerUrl.getProviderId(), i.getUrl() ) ) {
						ProviderUrl img = new ProviderUrl( providerUrl.getProviderId(), i.getUrl(), 4, 4 ); // image new
						img.setParentId( providerUrl.getId() );
						img.getParams().put( "width", i.getWidth() );
						img.getParams().put( "height", i.getHeight() );
						img.getParams().put( "external_id", i.getExternalId() );
						img.getParams().put( "alt", i.getAlt() );

						crawler.getProviderService().createUrl( img );
					}
				}

				ProviderData item = new ProviderData();
				item.setUrlId( providerUrl.getId() );
				item.setLanguageId( "ru" );
				item.setTypeId( providerUrl.getTypeId() );
				item.setProviderId( providerUrl.getProviderId() );

				Map<String, String> data = item.getData();
				data.put( "header", parser.header() );
				data.put( "short_description", parser.shortDescription() );
				data.put( "full_description", parser.fullDescription() );

				data.put( "status", parser.status() );
				data.put( "status_text", parser.statusText() );

				data.put( "contact_address", parser.contactsAddress() );
				data.put( "contact_phone", parser.contactsPhone() );
				data.put( "contact_link", parser.contactsLink() );
				data.put( "contact_worktime", parser.contactsWorktime() );

				data.put( "rating", parser.rating() );

				data.put( "longitude", parser.location().getLongitude() );
				data.put( "latitude", parser.location().getLongitude() );

				crawler.getProviderService().saveProviderData( item );

				// crawler.getProviderService().clearProviderDataCommentsByParentId( item.getId() );

				// for( Comment c : parser.comments() ) {
				// System.err.println( c );
				//
				// ProviderData comment = new ProviderData();
				// comment.setUrlId( item.getUrlId() );
				// comment.setParentId( item.getId() );
				// comment.setProviderId( providerUrl.getProviderId() );
				// comment.setLanguageId( "ru" );
				// comment.setTypeId( 7 ); // comment
				//
				// Map<String, String> cdata = item.getData();
				// cdata.put( "header", c.getHeader() );
				// cdata.put( "comment", c.getBody() );
				//
				// crawler.getProviderService().saveProviderData( comment );
				// }

				// System.err.println( parser.comments() );
				// System.err.println( parser.images() );

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
