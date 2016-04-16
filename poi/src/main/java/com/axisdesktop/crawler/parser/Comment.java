package com.axisdesktop.crawler.parser;

public class Comment {
	private String header;
	private String body;
	private String date;
	private User user;
	private String externalId;
	private String parentExternalId;

	public static class Builder {
		private Comment comment;

		public Builder() {
			comment = new Comment();
		}

		public Builder header( String header ) {
			comment.setHeader( header );
			return this;
		}

		public Builder body( String body ) {
			comment.setBody( body );
			return this;
		}

		public Builder date( String date ) {
			comment.setDate( date );
			return this;
		}

		public Builder user( User user ) {
			comment.setUser( user );
			return this;
		}

		public Builder extId( String id ) {
			comment.setExternalId( id );
			return this;
		}

		public Builder extParentId( String id ) {
			comment.setParentExternalId( id );
			return this;
		}

		public Comment build() {
			if( comment.getBody() == null || comment.getBody().length() == 0 ) {
				throw new IllegalStateException( "comment's body is empty" );
			}

			return comment;
		}
	}

	public String getHeader() {
		return header;
	}

	public void setHeader( String header ) {
		this.header = header;
	}

	public String getBody() {
		return body;
	}

	public void setBody( String body ) {
		this.body = body;
	}

	public String getDate() {
		return date;
	}

	public void setDate( String date ) {
		this.date = date;
	}

	public User getUser() {
		return user;
	}

	public void setUser( User user ) {
		this.user = user;
	}

	public String getExternalId() {
		return externalId;
	}

	public void setExternalId( String externalId ) {
		this.externalId = externalId;
	}

	public String getParentExternalId() {
		return parentExternalId;
	}

	public void setParentExternalId( String parentExternalId ) {
		this.parentExternalId = parentExternalId;
	}

	@Override
	public String toString() {
		return "Comment [header=" + header + ", body=" + body + ", date=" + date + ", user=" + user + ", externalId="
				+ externalId + ", parentExternalId=" + parentExternalId + "]";
	}

}
