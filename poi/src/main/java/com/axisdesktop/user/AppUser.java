package com.axisdesktop.user;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.social.security.SocialUser;

@SuppressWarnings( "serial" )
public class AppUser extends SocialUser {
	private long id;
	private String firstName;
	private String lastName;
	private Role role;
	private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail( String email ) {
		this.email = email;
	}

	private SocialMediaService socialSignInProvider;

	public enum Role {
		ROLE_USER
	}

	public enum SocialMediaService {
		FACEBOOK, TWITTER
	}

	public AppUser( String username, String password, Collection<? extends GrantedAuthority> authorities ) {
		super( username, password, authorities );
		// TODO Auto-generated constructor stub
	}

	public static class Builder {
		private long id;

		private String username;

		private String firstName;

		private String lastName;

		private String password;

		private Role role;

		private String email;

		private SocialMediaService socialSignInProvider;

		private Set<GrantedAuthority> authorities;

		public Builder() {
			this.authorities = new HashSet<>();
		}

		public Builder firstName( String firstName ) {
			this.firstName = firstName;
			return this;
		}

		public Builder id( Long id ) {
			this.id = id;
			return this;
		}

		public Builder lastName( String lastName ) {
			this.lastName = lastName;
			return this;
		}

		public Builder password( String password ) {
			if( password == null ) {
				password = "SocialUser";
			}

			this.password = password;
			return this;
		}

		public Builder role( Role role ) {
			this.role = role;

			SimpleGrantedAuthority authority = new SimpleGrantedAuthority( role.toString() );
			this.authorities.add( authority );

			return this;
		}

		public Builder socialSignInProvider( SocialMediaService socialSignInProvider ) {
			this.socialSignInProvider = socialSignInProvider;
			return this;
		}

		public Builder username( String username ) {
			this.username = username;
			return this;
		}

		public AppUser build() {
			AppUser user = new AppUser( username, password, authorities );

			user.id = id;
			user.firstName = firstName;
			user.lastName = lastName;
			user.role = role;
			user.socialSignInProvider = socialSignInProvider;

			return user;
		}
	}

	public long getId() {
		return id;
	}

	public void setId( long id ) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName( String firstName ) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName( String lastName ) {
		this.lastName = lastName;
	}

	public Role getRole() {
		return role;
	}

	public void setRole( Role role ) {
		this.role = role;
	}

	public SocialMediaService getSocialSignInProvider() {
		return socialSignInProvider;
	}

	public void setSocialSignInProvider( SocialMediaService socialSignInProvider ) {
		this.socialSignInProvider = socialSignInProvider;
	}

}
