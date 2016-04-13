package com.axisdesktop.crawler.parser;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class Parser {
	public static final Logger logger = LoggerFactory.getLogger( Parser.class );

	public String metaTitle() {
		logger.info( "metaTitle() unimplemented" );
		return null;
	}

	public String metaKeywords() {
		logger.info( "metaKeywords() unimplemented" );
		return null;
	}

	public String metaDescription() {
		logger.info( "metaDescription() unimplemented" );
		return null;
	}

	// page name
	public String header() {
		logger.info( "header() unimplemented" );
		return null;
	}

	public String shortDescription() {
		logger.info( "shortDescription() unimplemented" );
		return null;
	}

	public String fullDescription() {
		logger.info( "fullDescription() unimplemented" );
		return null;
	}

	public Location location() {
		logger.info( "location() unimplemented" );
		return null;
	}

	public String contacts() {
		logger.info( "contacts() unimplemented" );
		return null;
	}

	public String contactsAddress() {
		logger.info( "contactsAddress() unimplemented" );
		return null;
	}

	public String contactsLink() {
		logger.info( "contactsLink() unimplemented" );
		return null;
	}

	public String contactsEmail() {
		logger.info( "contactsEmail() unimplemented" );
		return null;
	}

	public String contactsPhone() {
		logger.info( "contactsPhone() unimplemented" );
		return null;
	}

	public String contactsWorktime() {
		logger.info( "contactsWorktime() unimplemented" );
		return null;
	}

	public String status() {
		logger.info( "status() unimplemented" );
		return null;
	}

	public String statusText() {
		logger.info( "statusText() unimplemented" );
		return null;
	}

	public String rating() {
		logger.info( "rating() unimplemented" );
		return null;
	}

	public BigDecimal price() {
		logger.info( "price() unimplemented" );
		return null;
	}

	public BigDecimal priceOld() {
		logger.info( "priceOld() unimplemented" );
		return null;
	}

	public List<Image> images() {
		logger.info( "images() unimplemented" );
		return null;
	}

	public List<Comment> comments() {
		logger.info( "comments() unimplemented" );
		return null;
	}

	public List<String> tags() {
		logger.info( "tags() unimplemented" );
		return null;
	}

	public Set<String> categoryLinks() {
		logger.info( "[INFO] categoryLinks() unimplemented" );
		return null;
	}

	public Set<String> itemLinks() {
		logger.info( "[INFO] itemLinks() unimplemented" );
		return null;
	}

	@Override
	public String toString() {
		return "Parser [metaTitle()=" + metaTitle() + ", metaKeywords()=" + metaKeywords() + ", metaDescription()="
				+ metaDescription() + ", header()=" + header() + ", shortDescription()=" + shortDescription()
				+ ", fullDescription()=" + fullDescription() + ", location()=" + location() + ", contacts()="
				+ contacts() + ", contactsLink()=" + contactsLink() + ", contactsEmail()=" + contactsEmail()
				+ ", contactsPhone()=" + contactsPhone() + ", status()=" + status() + ", statusText()=" + statusText()
				+ ", rating()=" + rating() + ", price()=" + price() + ", priceOld()=" + priceOld() + ", images()="
				+ images() + ", comments()=" + comments() + ", tags()=" + tags() + ", categoryLinks()="
				+ categoryLinks() + ", itemLinks()=" + itemLinks() + "]";
	}

}
