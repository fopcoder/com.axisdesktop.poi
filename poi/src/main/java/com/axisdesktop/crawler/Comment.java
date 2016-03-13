package com.axisdesktop.crawler;

import java.util.Calendar;

public class Comment {
	private String header;
	private String body;
	private Calendar date;
	private User user;
	private int externalId;
	private int parentExternalId;

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

		public Builder date( Calendar date ) {
			comment.setDate( date );
			return this;
		}

		public Builder user( User user ) {
			comment.setUser( user );
			return this;
		}

		public Builder extId( int id ) {
			comment.setExtId( id );
			return this;
		}

		public Builder extParentId( int id ) {
			comment.setParentExtId( id );
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

	public Calendar getDate() {
		return date;
	}

	public void setDate( Calendar date ) {
		this.date = date;
	}

	public User getUser() {
		return user;
	}

	public void setUser( User user ) {
		this.user = user;
	}

	public int getExtId() {
		return externalId;
	}

	public void setExtId( int extId ) {
		this.externalId = extId;
	}

	public int getParentExtId() {
		return parentExternalId;
	}

	public void setParentExtId( int parentExtId ) {
		this.parentExternalId = parentExtId;
	}

	@Override
	public String toString() {
		return "Comment [header=" + header + ", body=" + body + ", date=" + date + ", user=" + user + ", externalId="
				+ externalId + ", parentExternalId=" + parentExternalId + "]";
	}

}
