package com.axisdesktop.crawler;

public class Image {
	private String uri;
	private int width;
	private int height;
	private int extId;
	private String alt;

	public Image() {
	}

	public static class Builder {
		Image img = new Image();

		public Builder uri( String url ) {
			img.setUri( url );
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
			if( img.getUri() == null ) {
				throw new IllegalStateException( "bad image uri" );
			}

			return img;
		}
	}

	public String getUri() {
		return uri;
	}

	public void setUri( String url ) {
		this.uri = url;
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
		return extId;
	}

	public void setExtId( int extId ) {
		this.extId = extId;
	}

	public String getAlt() {
		return alt;
	}

	public void setAlt( String alt ) {
		this.alt = alt;
	}

	@Override
	public String toString() {
		return "Image [uri=" + uri + ", width=" + width + ", height=" + height + ", extId=" + extId + ", alt=" + alt
				+ "]";
	}

}
