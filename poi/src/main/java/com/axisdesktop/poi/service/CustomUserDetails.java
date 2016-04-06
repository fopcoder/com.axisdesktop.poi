package com.axisdesktop.poi.service;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;

import com.axisdesktop.poi.entity.User;

@SuppressWarnings( "serial" )
public class CustomUserDetails extends User implements UserDetails {
	private List<String> roles;

	public CustomUserDetails( User user, List<String> roles ) {
		super( user );
		this.roles = roles;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		String r = StringUtils.collectionToCommaDelimitedString( roles );
		return AuthorityUtils.commaSeparatedStringToAuthorityList( r );
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return super.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
