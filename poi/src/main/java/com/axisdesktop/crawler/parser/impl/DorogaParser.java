package com.axisdesktop.crawler.parser.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.axisdesktop.crawler.parser.Comment;
import com.axisdesktop.crawler.parser.Image;
import com.axisdesktop.crawler.parser.Location;
import com.axisdesktop.crawler.parser.Parser;
import com.axisdesktop.crawler.parser.User;

public class DorogaParser extends Parser {
	private Document doc;

	public DorogaParser( String txt ) {
		doc = Jsoup.parse( txt );
	}

	@Override
	public Location location() {
		// 48°25'50.3''N, 22°41'15.7''E
		String[] latlng = doc.select( "table div span:matches(\\d{1,2}°)" ).first().text().replaceAll( "''", "\"" )
				.replaceAll( "\\s+", "" ).split( "," );

		if( latlng.length != 2 ) {
			throw new IllegalArgumentException( "must be 2 coords: " + Arrays.toString( latlng ) );
		}

		for( String c : latlng ) {
			if( !c.matches( "\\d+\\W\\d+\\W[\\w\\.]+\\W\\w" ) ) {
				throw new IllegalArgumentException( "bad coords: " + c );
			}
		}

		String[] lat = latlng[0].split( "[^\\d\\.\\w]" );
		String[] lng = latlng[1].split( "[^\\d\\.\\w]" );

		double dlat = ( Double.parseDouble( lat[0] ) + Double.parseDouble( lat[1] ) / 60
				+ Double.parseDouble( lat[2] ) / 3600 ) * ( lat[3].matches( "(?i)n" ) ? 1 : -1 );
		double dlng = ( Double.parseDouble( lng[0] ) + Double.parseDouble( lng[1] ) / 60
				+ Double.parseDouble( lng[2] ) / 3600 ) * ( lng[3].matches( "(?i)e" ) ? 1 : -1 );

		Location loc = new Location( dlat, dlng );

		return loc;
	}

	@Override
	public String header() {
		String h = doc.select( "td.TitleText" ).first().text();
		return h;
	}

	@Override
	public String contacts() {
		List<String> l = new LinkedList<>();

		Element adrEl = doc.select( "td.main-column table div:contains(Адрес)" ).first();
		Element phnEl = doc.select( "td.main-column table div:matches(\\d+-\\d+-\\d+)" ).first();
		Element wtEl = doc.select( "td.main-column table div:contains(Работает)" ).first();

		if( adrEl != null ) {
			l.add( adrEl.text() );
		}
		if( phnEl != null ) {
			l.add( phnEl.text() );
		}
		if( wtEl != null ) {
			l.add( wtEl.text() );
		}

		return String.join( "\n", l );
	}

	@Override
	public String contactsLink() {
		Element e = doc.select( "td.main-column table noindex a[class=NormalLink][target=_blank]" ).first();

		if( e != null ) {
			return e.attr( "href" );
		}

		return "";
	}

	@Override
	public int status() {
		Element e = doc.select( "td.main-column table div[class*=IconRouteBig]" ).first();

		if( e != null ) {
			for( String cn : e.classNames() ) {
				if( cn.contains( "IconRouteBig" ) ) {
					return Integer.parseInt( cn.replaceAll( "(?i)IconRouteBig", "" ) );
				}
			}
		}

		return 0;
	}

	@Override
	public String statusText() {
		Element e = doc.select( "td.main-column table div[class*=IconRouteBig]" ).first();

		if( e != null ) {
			return e.attr( "title" );
		}

		return "";
	}

	@Override
	public BigDecimal rating() {
		Element e = doc.select( "table span[class=RatingBigText]" ).first();

		if( e != null ) {
			return new BigDecimal( e.text() );
		}

		return null;
	}

	@Override
	public String shortDescription() {
		Element e = doc.select( "td.main-column.center  div:nth-child(2) > div:nth-child(1) > div" ).first();

		if( e != null ) {
			return e.text();
		}

		return "";
	}

	@Override
	public String fullDescription() {
		Element e = doc.select( "td.main-column.center div > div.NormalText10pt" ).first();

		if( e != null ) {
			return e.text();
		}

		return "";
	}

	@Override
	public List<Image> images() {
		List<Image> list = new LinkedList<>();
		Elements images = doc.select( "td.main-column.center div#imageBox div div" );
		Pattern p = Pattern.compile( "CatalogPOIContentImageID=(\\d+)" );

		for( Element e : images ) {
			Element t = e.select( "a" ).first();
			Matcher m = p.matcher( t.attr( "href" ) );

			if( m.find() ) {
				Image img = new Image.Builder().url( t.attr( "href" ) ).alt( t.attr( "title" ) )
						.extId( Integer.parseInt( m.group( 1 ) ) ).build();
				list.add( img );
			}
		}

		return list;
	}

	@Override
	public List<Comment> comments() {
		List<Comment> list = new LinkedList<>();

		Elements comments = doc.select( "span[id*=uccFeedbacks] > table tr" );
		Pattern userPattern = Pattern.compile( "UserID=(\\d+)" );

		DateTimeFormatter dateFormatter = DateTimeFormatter.ofLocalizedDate( FormatStyle.FULL )
				.withLocale( new Locale( "ru" ) );

		for( Element c : comments ) {
			User user = new User();
			Element tdEl = c.select( "td" ).first();
			Element userEl = tdEl.select( "div > a" ).first();

			if( userEl != null ) {
				user.setName( userEl.text() );

				Matcher um = userPattern.matcher( userEl.attr( "href" ) );

				if( um.find() ) {
					user.setExtId( Integer.parseInt( um.group( 1 ) ) );
				}

				user.setImageUri( "/" + tdEl.select( "img" ).attr( "src" ).replaceAll( "\\.\\.\\/", "" ) );

				String body = c.select( "td" ).get( 1 ).select( "div" ).last().text();

				Calendar date = null;
				try {
					String dt = c.select( "td table td" ).first().text().trim();

					LocalDate ld = LocalDate.parse( dt, dateFormatter );
					date = Calendar.getInstance( TimeZone.getTimeZone( "Europe/Kiev" ) );
					date.set( ld.getYear(), ld.getMonthValue(), ld.getDayOfMonth(), 0, 0, 0 );
				}
				catch( Exception e ) {/* ignore error */}

				Comment comment = new Comment.Builder().date( date ).body( body ).user( user ).build();

				list.add( comment );
			}
		}

		return list;
	}

	@Override
	public List<String> tags() {
		List<String> list = new LinkedList<>();
		Element e = doc.select( "td.main-column.center  div:nth-child(2) > div:nth-child(1) > div" ).first();

		if( e != null ) {
			list.add( e.text().split( "," )[0].trim() );
		}

		return list;
	}

	// @Override
	// public Set<Category> categoryLinks() {
	// Set<Category> list = new HashSet<>();
	//
	// Elements elts = doc.select( "loc:contains(/pois/)" );
	//
	// for( Element e : elts ) {
	// String[] params = e.text().replaceAll( ".*pois/", "" ).split( "/" );
	//
	// if( params[params.length - 1].matches( "^\\d+$" ) ) {
	// String uri = e.text();
	// int extId = Integer.parseInt( params[params.length - 1] );
	// String uriPart = params[params.length - 2];
	// int level = params.length - 2;
	// String parentUriPart = null;
	//
	// if( level > 0 ) {
	// parentUriPart = params[params.length - 3];
	// }
	//
	// Category cat = new Category( uriPart, uriPart, uri, extId, parentUriPart, level );
	// if( !list.contains( cat ) ) {
	// list.add( cat );
	// }
	// }
	// }
	//
	// return list;
	// }

	@Override
	public Set<String> categoryLinks() {
		Set<String> list = new HashSet<>();

		Elements elts = doc.select( "loc:contains(/pois/)" );

		for( Element e : elts ) {
			String uri = e.text();

			if( !list.contains( uri ) ) {
				list.add( uri );
			}
		}

		return list;
	}

	@Override
	public Set<String> itemLinks() {
		Set<String> list = new HashSet<>();

		Elements elts = doc.select( "loc:contains(/poi/)" );

		for( Element e : elts ) {
			String uri = e.text();

			if( !list.contains( uri ) ) {
				list.add( uri );
			}
		}

		return list;
	}
}
