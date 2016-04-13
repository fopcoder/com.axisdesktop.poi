package com.axisdesktop.crawler.entity;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import com.axisdesktop.base.entity.BaseEntity;

@Entity
@Table( name = "provider_data", schema = "crawler" )
@TypeDef( name = "hstore", typeClass = HstoreUserType.class )
@NamedQueries( {
		@NamedQuery( name = "ProviderData.getIdByUrlId", query = "SELECT d.id FROM ProviderData d WHERE urlId = :urlId" ),
		@NamedQuery( name = "ProviderData.clearCommentsByParentId", query = "DELETE FROM ProviderData WHERE parentId = :parentId" ) } )
public class ProviderData extends BaseEntity<Long> {

	@Column( name = "url_id" )
	private long urlId;

	@Column( name = "provider_id" )
	private int providerId;

	@Column( name = "category_id" )
	private int categoryId;

	@Column( name = "language_id" )
	private String languageId;

	@Column( name = "parent_id" )
	private long parentId;

	@Column( name = "type_id" )
	private int typeId;

	@Type( type = "hstore" )
	private Map<String, String> data = new HashMap<>();

	public Map<String, String> getData() {
		return data;
	}

	public void setData( Map<String, String> data ) {
		this.data = data;
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

	public String getLanguageId() {
		return languageId;
	}

	public void setLanguageId( String languageId ) {
		this.languageId = languageId;
	}

	public long getParentId() {
		return parentId;
	}

	public void setParentId( long parentId ) {
		this.parentId = parentId;
	}

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId( int typeId ) {
		this.typeId = typeId;
	}

	public int getProviderId() {
		return providerId;
	}

	public void setProviderId( int providerId ) {
		this.providerId = providerId;
	}

	@Override
	public String toString() {
		return "ProviderData [urlId=" + urlId + ", providerId=" + providerId + ", categoryId=" + categoryId
				+ ", languageId=" + languageId + ", parentId=" + parentId + ", typeId=" + typeId + ", data=" + data
				+ "]";
	}

}
