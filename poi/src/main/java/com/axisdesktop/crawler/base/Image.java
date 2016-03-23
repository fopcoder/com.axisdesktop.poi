package com.axisdesktop.crawler.base;

public class Image {
	private String url;
	private int width;
	private int height;
	private int externalId;
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

		public Builder width( int width ) {
			img.setWidth( width );
			return this;
		}

		public Builder height( int height ) {
			img.setHeight( height );
			return this;
		}

		public Builder extId( int id ) {
			img.setExtId( id );
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

	public int getWidth() {
		return width;
	}

	public void setWidth( int width ) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight( int height ) {
		this.height = height;
	}

	public int getExtId() {
		return externalId;
	}

	public void setExtId( int extId ) {
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
		return "Image [url=" + url + ", width=" + width + ", height=" + height + ", externalId=" + externalId + ", alt=" + alt
				+ "]";
	}

}
