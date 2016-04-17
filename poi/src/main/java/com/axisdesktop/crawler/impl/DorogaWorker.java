package com.axisdesktop.crawler.impl;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.Proxy;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.axisdesktop.crawler.base.Crawler;
import com.axisdesktop.crawler.base.Worker;
import com.axisdesktop.crawler.entity.ProviderData;
import com.axisdesktop.crawler.entity.ProviderUrl;
import com.axisdesktop.crawler.parser.Comment;
import com.axisdesktop.crawler.parser.Image;
import com.axisdesktop.crawler.parser.Parser;
import com.axisdesktop.crawler.parser.impl.DorogaParser;
import com.axisdesktop.utils.HttpUtils;

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
			// String text = CrawlerUtils.getCrawlerUrlTextContent( crawler, providerUrl );

			String text = null;

			Proxy proxy = crawler.getProxyService().getRandomActiveProxy();

			if( proxy == null ) {
				throw new IOException( "active proxy not found!" );
			}

			Map<String, String> connProps = crawler.getConnectionProperties();
			connProps.put( "user-agent", crawler.getUserAgent() );
			connProps.put( "referer", crawler.getReferer() );

			HttpURLConnection uc = HttpUtils.getConnection( providerUrl.getUrl(), proxy, connProps );

			if( uc.getResponseCode() != 200 ) {
				throw new IllegalStateException(
						String.format( "%d\n%s", uc.getResponseCode(), uc.getResponseMessage() ) );
			}

			logger.debug( uc.getContentType() );

			if( uc.getContentType().contains( "text" ) ) {
				text = HttpUtils.getTextFromConnection( uc );
			}
			else if( uc.getContentType().contains( "image" ) ) {
				// TODO сделать нормально
				String ext = null;

				if( uc.getContentType().contains( "jpeg" ) ) {
					ext = "jpg";
				}
				else if( uc.getContentType().contains( "png" ) ) {
					ext = "png";
				}
				else if( uc.getContentType().contains( "gif" ) ) {
					ext = "gif";
				}
				else {
					ext = "jpg";
				}

				// TODO сделать параметром на сервере
				String baseUrl = "C:\\Temp\\crawler";

				Path baseDir = Paths.get( baseUrl );

				if( Files.notExists( baseDir ) ) {
					Files.createDirectory( baseDir );
				}

				Path imgDir = Paths.get( baseUrl, Long.toString(
						providerUrl.getParentId() != null ? providerUrl.getParentId() : providerUrl.getId() ) );

				if( Files.notExists( imgDir ) ) {
					Files.createDirectory( imgDir );
				}

				Path imgFile = Paths.get( imgDir.toString(), Long.toString( providerUrl.getId() ) + "." + ext );

				logger.debug( imgFile.toString() );

				Files.copy( uc.getInputStream(), imgFile, REPLACE_EXISTING );
			}

			if( text != null ) {
				Parser parser = new DorogaParser( text );

				for( Image i : parser.images() ) {
					if( !crawler.getProviderService().isUrlExist( providerUrl.getProviderId(), i.getUrl() ) ) {
						ProviderUrl img = new ProviderUrl( providerUrl.getProviderId(), i.getUrl(), 4, 4 ); // image new
						img.setParentId( providerUrl.getId() );
						// img.getParams().put( "width", i.getWidth() );
						// img.getParams().put( "height", i.getHeight() );
						img.getParams().put( "external_id", i.getExternalId() );
						img.getParams().put( "alt", i.getAlt() );

						crawler.getProviderService().createUrl( img );
					}
				}

				ProviderData item = new ProviderData( providerUrl.getProviderId(), providerUrl.getId(),
						providerUrl.getTypeId(), "ru" );

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

				crawler.getProviderService().clearProviderDataCommentsByParentId( item.getId() );

				for( Comment c : parser.comments() ) {
					ProviderData comment = new ProviderData( providerUrl.getProviderId(), providerUrl.getId(), 7,
							"ru" );
					comment.setParentId( item.getId() );

					comment.getData().put( "date", c.getDate() );
					comment.getData().put( "comment", c.getBody() );
					comment.getData().put( "user", c.getUser().getName() );
					comment.getData().put( "user_id", c.getUser().getExternalId() );
					comment.getData().put( "user_img", c.getUser().getImageUri() );

					crawler.getProviderService().createProviderData( comment );
				}

				if( !parser.tags().isEmpty() ) {
					ProviderData tag = new ProviderData( providerUrl.getProviderId(), providerUrl.getId(), 8, "ru" );
					tag.setParentId( item.getId() );
					tag.getData().put( "tags", String.join( ",", parser.tags() ) );

					crawler.getProviderService().saveProviderData( tag );
				}
			}

			providerUrl.setLog( null );
			providerUrl.setTries( 0 );

			if( providerUrl.getTypeId() == 1 ) { // feed
				providerUrl.setStatusId( 1 ); // active
			}
			else {
				providerUrl.setStatusId( 5 ); // downloaded
			}

			crawler.getProviderService().updateUrl( providerUrl );
		}
		catch( Exception e ) {
			providerUrl.setLog( e.toString() );
			providerUrl.setStatusId( 3 );
			providerUrl.setTries( providerUrl.getTries() + 1 );

			crawler.getProviderService().updateUrl( providerUrl );
			e.printStackTrace();
			logger.debug( e.getMessage() );
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
