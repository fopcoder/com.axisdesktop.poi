package com.axisdesktop.crawler.entity;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
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
	@Column( name = "url_id" )
	private long urlId;

	@Column( name = "category_id" )
	private int categoryId;

	@Column( updatable = false )
	@Temporal( TemporalType.TIMESTAMP )
	private Calendar created;

	@Temporal( TemporalType.TIMESTAMP )
	private Calendar modified;

	@Column( name = "meta_title" )
	private String metaTitle;

	@Column( name = "meta_keywords" )
	private String metaKeywords;

	@Column( name = "meta_description" )
	private String metaDescription;

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

	@Column( name = "contacts_email" )
	private String contactsEmail;

	@Column( name = "contacts_phone" )
	private String contactsPhone;

	private BigDecimal rating;
	private BigDecimal price;

	@Column( name = "price_old" )
	private BigDecimal priceOld;

	@PrePersist
	private void prePersist() {
		this.created = this.modified = Calendar.getInstance();
	}

	@PreUpdate
	private void preUpdate() {
		this.modified = Calendar.getInstance();
	}

	public static class Builder {
		ProviderData data = new ProviderData();

		public Builder categoryId( int id ) {
			data.setCategoryId( id );
			return this;
		}

		public Builder metaTitle( String txt ) {
			data.setMetaTitle( txt );
			return this;
		}

		public Builder metaKeywords( String txt ) {
			data.setMetaKeywords( txt );
			return this;
		}

		public Builder metaDescription( String txt ) {
			data.setMetaDescription( txt );
			return this;
		}

		public Builder header( String txt ) {
			data.setHeader( txt );
			return this;
		}

		public Builder shortDescription( String txt ) {
			data.setShortDescription( txt );
			return this;
		}

		public Builder fullDescription( String txt ) {
			data.setFullDescription( txt );
			return this;
		}

		public Builder latitude( BigDecimal lat ) {
			data.setLatitude( lat );
			return this;
		}

		public Builder longitude( BigDecimal lng ) {
			data.setLongitude( lng );
			return this;
		}

		public Builder status( int val ) {
			data.setStatus( val );
			return this;
		}

		public Builder statusText( String txt ) {
			data.setStatusText( txt );
			return this;
		}

		public Builder contacts( String txt ) {
			data.setContacts( txt );
			return this;
		}

		public Builder contactsLink( String txt ) {
			data.setContactsLink( txt );
			return this;
		}

		public Builder contactsEmail( String txt ) {
			data.setContactsEmail( txt );
			return this;
		}

		public Builder contactsPhone( String txt ) {
			data.setContactsPhone( txt );
			return this;
		}

		public Builder rating( BigDecimal val ) {
			data.setRating( val );
			return this;
		}

		public Builder price( double val ) {
			data.setPrice( BigDecimal.valueOf( val ) );
			return this;
		}

		public Builder priceOld( double val ) {
			data.setPriceOld( BigDecimal.valueOf( val ) );
			return this;
		}

		public Builder urlId( long val ) {
			data.setUrlId( val );
			return this;
		}

		public ProviderData build() {
			return data;
		}
	}

	public long getUrlId() {
		return urlId;
	}

	public void setUrlId( long urlId ) {
		this.urlId = urlId;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId( int categoryId ) {
		this.categoryId = categoryId;
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

	public String getMetaTitle() {
		return metaTitle;
	}

	public void setMetaTitle( String metaTitle ) {
		this.metaTitle = metaTitle;
	}

	public String getMetaKeywords() {
		return metaKeywords;
	}

	public void setMetaKeywords( String metaKeywords ) {
		this.metaKeywords = metaKeywords;
	}

	public String getMetaDescription() {
		return metaDescription;
	}

	public void setMetaDescription( String metaDescription ) {
		this.metaDescription = metaDescription;
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

	public String getContactsEmail() {
		return contactsEmail;
	}

	public void setContactsEmail( String contactsEmail ) {
		this.contactsEmail = contactsEmail;
	}

	public String getContactsPhone() {
		return contactsPhone;
	}

	public void setContactsPhone( String contactsPhone ) {
		this.contactsPhone = contactsPhone;
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

	@Override
	public String toString() {
		return "ProviderData [urlId=" + urlId + ", categoryId=" + categoryId + ", created=" + created + ", modified="
				+ modified + ", metaTitle=" + metaTitle + ", metaKeywords=" + metaKeywords + ", metaDescriprion="
				+ metaDescription + ", header=" + header + ", shortDescription=" + shortDescription
				+ ", fullDescription=" + fullDescription + ", latitude=" + latitude + ", longitude=" + longitude
				+ ", status=" + status + ", statusText=" + statusText + ", contacts=" + contacts + ", contactsLink="
				+ contactsLink + ", contactsEmail=" + contactsEmail + ", contactsPhone=" + contactsPhone + ", rating="
				+ rating + ", price=" + price + ", priceOld=" + priceOld + "]";
	}

}
