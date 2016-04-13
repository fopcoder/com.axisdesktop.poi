package com.axisdesktop.crawler.parser;

public class Image {
	private String url;
	private String width;
	private String height;
	private String externalId;
	private String alt;

	public Image() {
	}

	public static class Builder {
		Image img = new Image();

		public Builder url( String url ) {
			img.setUrl( url );
			return this;
		}

		public Builder alt( String alt ) {
			img.setAlt( alt );
			return this;
		}

		public Builder width( String width ) {
			img.setWidth( width );
			return this;
		}

		public Builder height( String height ) {
			img.setHeight( height );
			return this;
		}

		public Builder externalId( String id ) {
			img.setExternalId( id );
			return this;
		}

		public Image build() {
			if( img.getUrl() == null ) {
				throw new IllegalStateException( "bad image url" );
			}

			return img;
		}
	}

	public String getUrl() {
		return url;
	}

	public void setUrl( String url ) {
		this.url = url;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth( String width ) {
		this.width = width;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight( String height ) {
		this.height = height;
	}

	public String getExternalId() {
		return externalId;
	}

	public void setExternalId( String extId ) {
		this.externalId = extId;
	}

	public String getAlt() {
		return alt;
	}

	public void setAlt( String alt ) {
		this.alt = alt;
	}

	@Override
	public String toString() {
		return "Image [url=" + url + ", width=" + width + ", height=" + height + ", externalId=" + externalId + ", alt="
				+ alt + "]";
	}

}
