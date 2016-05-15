package com.axisdesktop.crawler.impl;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

import java.net.HttpURLConnection;
import java.net.Proxy;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.axisdesktop.crawler.base.Crawler;
import com.axisdesktop.crawler.base.Worker;
import com.axisdesktop.crawler.entity.ProviderData;
import com.axisdesktop.crawler.entity.ProviderUrl;
import com.axisdesktop.crawler.exception.NoActiveProxyException;
import com.axisdesktop.crawler.parser.Comment;
import com.axisdesktop.crawler.parser.Image;
import com.axisdesktop.crawler.parser.Parser;
import com.axisdesktop.crawler.parser.impl.DorogaParser;
import com.axisdesktop.poi.config.AppConf;
import com.axisdesktop.utils.HttpUtils;

public class DorogaWorker implements Worker {
	private static final Logger logger = LoggerFactory.getLogger( DorogaWorker.class );

	private Crawler crawler;
	private ProviderUrl providerUrl;

	@Autowired
	private AppConf appConf;

	public DorogaWorker( Crawler crawler, ProviderUrl providerUrl ) {
		this.crawler = crawler;
		this.providerUrl = providerUrl;
	}

	@Override
	public void run() {
		try {
			String text = null;
			Proxy proxy = crawler.getProxyService().getRandomActiveProxy();

			if( proxy == null ) {
				throw new NoActiveProxyException();
			}

			HttpURLConnection uc = crawler.getConnection( proxy, providerUrl );

			if( uc.getResponseCode() != 200 ) {
				throw new IllegalStateException(
						String.format( "status != 200:\n%d\n%s", uc.getResponseCode(), uc.getResponseMessage() ) );
			}

			if( uc.getContentType().contains( "text" ) ) {
				text = crawler.getTextContent( uc );

				if( text == null || text.equals( "" ) ) {
					throw new IllegalStateException( String.format( "received text is empty:" ) );
				}

				Parser parser = new DorogaParser( text );

				for( Image i : parser.images() ) {
					if( !crawler.getProviderService().isUrlExist( providerUrl.getProviderId(), i.getUrl() ) ) {
						ProviderUrl img = new ProviderUrl( providerUrl.getProviderId(), i.getUrl(), 4, 4 ); // image new
						img.setParentId( providerUrl.getId() );
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
				data.put( "latitude", parser.location().getLatitude() );

				crawler.getProviderService().saveProviderData( item );

				crawler.getProviderService().clearProviderDataCommentsByParentId( item.getId() );

				for( Comment c : parser.comments() ) {
					ProviderData comment = new ProviderData( providerUrl.getProviderId(), providerUrl.getId(), 7,
							"ru" );
					try {
						comment.setParentId( item.getId() );
						comment.getData().put( "date", c.getDate() );
						comment.getData().put( "comment", c.getBody() );
						comment.getData().put( "user", c.getUser().getName() );
						comment.getData().put( "user_id", c.getUser().getExternalId() );
						comment.getData().put( "user_img", c.getUser().getImageUri() );

						crawler.getProviderService().createProviderData( comment );
					}
					catch( Exception e ) {
						logger.error( String.format( "save comment error:\n%s", comment.toString() ) );
					}
				}

				if( !parser.tags().isEmpty() ) {
					ProviderData tag = new ProviderData( providerUrl.getProviderId(), providerUrl.getId(), 8, "ru" );
					tag.setParentId( item.getId() );
					tag.getData().put( "tags", String.join( ",", parser.tags() ) );

					crawler.getProviderService().saveProviderData( tag );
				}
			}
			else if( uc.getContentType().contains( "image" ) ) {
				String ext = HttpUtils.mime2ext( uc.getContentType(), "jpg" );

				String baseUrl = appConf.getCrawlerFileStorePath();
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
		catch( IllegalStateException e ) {
			logger.error( e.toString(), e.getMessage(), this.providerUrl.toString() );
		}
		catch( NoActiveProxyException e ) {
			logger.warn( e.toString(), e.getMessage(), this.providerUrl.toString() );
		}
		catch( Exception e ) {
			logger.error( e.toString(), e.getMessage(), this.providerUrl.toString() );

			providerUrl.setLog( e.toString() + "\n" + e.getMessage() );
			providerUrl.setStatusId( 3 );
			providerUrl.setTries( providerUrl.getTries() + 1 );

			crawler.getProviderService().updateUrl( providerUrl );
			e.printStackTrace();
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
