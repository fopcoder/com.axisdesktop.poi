package com.axisdesktop.crawler.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.axisdesktop.base.entity.BaseEntity;

@Entity
@Table( name = "provider_data", schema = "crawler" )
public class ProviderData extends BaseEntity<Long> {

	@Column( name = "url_id" )
	private long urlId;

	@Column( name = "provider_id" )
	private int providerId;

	@Column( name = "category_id" )
	private int categoryId;

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

	@Column( name = "contacts_address" )
	private String contactsAddress;

	@Column( name = "contacts_link" )
	private String contactsLink;

	@Column( name = "contacts_email" )
	private String contactsEmail;

	@Column( name = "contacts_phone" )
	private String contactsPhone;

	@Column( name = "contacts_worktime" )
	private String contactsWorktime;

	private BigDecimal rating;
	private BigDecimal price;

	@Column( name = "price_old" )
	private BigDecimal priceOld;

	@Column( name = "language_id" )
	private String languageId;

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

		public Builder contactsAddress( String txt ) {
			data.setContactsAddress( txt );
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

		public Builder contactsWorktime( String txt ) {
			data.setContactsWorktime( txt );
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

		public Builder languageId( String txt ) {
			data.setLanguageId( txt );
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

	public String getContactsAddress() {
		return contactsAddress;
	}

	public void setContactsAddress( String contactsAddress ) {
		this.contactsAddress = contactsAddress;
	}

	public String getContactsWorktime() {
		return contactsWorktime;
	}

	public void setContactsWorktime( String contactsWorktime ) {
		this.contactsWorktime = contactsWorktime;
	}

	public String getLanguageId() {
		return languageId;
	}

	public void setLanguageId( String languageId ) {
		this.languageId = languageId;
	}

	@Override
	public String toString() {
		return "ProviderData [urlId=" + urlId + ", categoryId=" + categoryId + ", metaTitle=" + metaTitle
				+ ", metaKeywords=" + metaKeywords + ", metaDescription=" + metaDescription + ", header=" + header
				+ ", shortDescription=" + shortDescription + ", fullDescription=" + fullDescription + ", latitude="
				+ latitude + ", longitude=" + longitude + ", status=" + status + ", statusText=" + statusText
				+ ", contacts=" + contacts + ", contactsAddress=" + contactsAddress + ", contactsLink=" + contactsLink
				+ ", contactsEmail=" + contactsEmail + ", contactsPhone=" + contactsPhone + ", contactsWorktime="
				+ contactsWorktime + ", rating=" + rating + ", price=" + price + ", priceOld=" + priceOld
				+ ", languageId=" + languageId + "]";
	}

}
