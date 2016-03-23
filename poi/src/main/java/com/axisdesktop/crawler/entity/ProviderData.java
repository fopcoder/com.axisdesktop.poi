package com.axisdesktop.crawler.entity;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table( name = "provider_data", schema = "crawler" )
public class ProviderData {
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private long id;

	@Column( updatable = false )
	@Temporal( TemporalType.TIMESTAMP )
	private Calendar created;

	@Temporal( TemporalType.TIMESTAMP )
	private Calendar modified;

	@PrePersist
	private void prePersist() {
		this.created = this.modified = Calendar.getInstance();
	}

	@PreUpdate
	private void preUpdate() {
		this.modified = Calendar.getInstance();
	}

	private String title;
	private String header;

	@Column( name = "short_description" )
	private String shortDescription;

	@Column( name = "full_description" )
	private String fullDescription;

	private BigDecimal latitude;
	private BigDecimal longitude;
	private int status;

	@Column( name = "status_text" )
	private String statusText;

	private String contacts;

	@Column( name = "contacts_link" )
	private String contactsLink;

	private BigDecimal rating;
	private BigDecimal price;

	@Column( name = "price_old" )
	private BigDecimal priceOld;

	private long url_id;
	private int category_id;

	public long getId() {
		return id;
	}

	public void setId( long id ) {
		this.id = id;
	}

	public Calendar getCreated() {
		return created;
	}

	public void setCreated( Calendar created ) {
		this.created = created;
	}

	public Calendar getModified() {
		return modified;
	}

	public void setModified( Calendar modified ) {
		this.modified = modified;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle( String title ) {
		this.title = title;
	}

	public String getHeader() {
		return header;
	}

	public void setHeader( String header ) {
		this.header = header;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription( String shortDescription ) {
		this.shortDescription = shortDescription;
	}

	public String getFullDescription() {
		return fullDescription;
	}

	public void setFullDescription( String fullDescription ) {
		this.fullDescription = fullDescription;
	}

	public BigDecimal getLatitude() {
		return latitude;
	}

	public void setLatitude( BigDecimal latitude ) {
		this.latitude = latitude;
	}

	public BigDecimal getLongitude() {
		return longitude;
	}

	public void setLongitude( BigDecimal longitude ) {
		this.longitude = longitude;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus( int status ) {
		this.status = status;
	}

	public String getStatusText() {
		return statusText;
	}

	public void setStatusText( String statusText ) {
		this.statusText = statusText;
	}

	public String getContacts() {
		return contacts;
	}

	public void setContacts( String contacts ) {
		this.contacts = contacts;
	}

	public String getContactsLink() {
		return contactsLink;
	}

	public void setContactsLink( String contactsLink ) {
		this.contactsLink = contactsLink;
	}

	public BigDecimal getRating() {
		return rating;
	}

	public void setRating( BigDecimal rating ) {
		this.rating = rating;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice( BigDecimal price ) {
		this.price = price;
	}

	public BigDecimal getPriceOld() {
		return priceOld;
	}

	public void setPriceOld( BigDecimal priceOld ) {
		this.priceOld = priceOld;
	}

	public long getUrl_id() {
		return url_id;
	}

	public void setUrl_id( long url_id ) {
		this.url_id = url_id;
	}

	public int getCategory_id() {
		return category_id;
	}

	public void setCategory_id( int category_id ) {
		this.category_id = category_id;
	}

	@Override
	public String toString() {
		return "ProviderData [id=" + id + ", created=" + created + ", modified=" + modified + ", title=" + title
				+ ", header=" + header + ", shortDescription=" + shortDescription + ", fullDescription="
				+ fullDescription + ", latitude=" + latitude + ", longitude=" + longitude + ", status=" + status
				+ ", statusText=" + statusText + ", contacts=" + contacts + ", contactsLink=" + contactsLink
				+ ", rating=" + rating + ", price=" + price + ", priceOld=" + priceOld + ", url_id=" + url_id
				+ ", category_id=" + category_id + "]";
	}

}
