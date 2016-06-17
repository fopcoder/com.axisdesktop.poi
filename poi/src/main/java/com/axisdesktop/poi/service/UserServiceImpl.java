package com.axisdesktop.poi.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.axisdesktop.poi.entity.User;
import com.axisdesktop.poi.entity.Role;
import com.axisdesktop.poi.repository.UserRepository;
import com.axisdesktop.poi.repository.UserRoleRopository;

@Service
public class UserServiceImpl implements UserService {
	private final UserRepository userRepo;
	private final UserRoleRopository userRoleRepo;

	@Autowired
	public UserServiceImpl( UserRepository userRepo, UserRoleRopository userRoleRepo ) {
		this.userRepo = userRepo;
		this.userRoleRepo = userRoleRepo;
	}

	@Override
	public UserDetails loadUserByUsername( String username ) throws UsernameNotFoundException {
		// User user = userRepo.findByName( username );
		User user = userRepo.findByEmail( username );
		if( user == null ) {
			throw new UsernameNotFoundException( "user with name " + username + "not found" );
		}
		else {
			// List<String> roles = userRepo.findRole().stream().map( Role::getName ).collect( Collectors.toList()
			// );
			List<String> roles = user.getRoles().stream().map( Role::getName ).collect( Collectors.toList() );
			return new CustomUserDetails( user, roles );
		}
	}

}
