// package com.axisdesktop.user;
//
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.core.userdetails.UsernameNotFoundException;
// import org.springframework.stereotype.Service;
//
// import com.axisdesktop.poi.entity.AppUserEntity;
//
// public class AppUserService implements UserDetailsService {
//
// private AppUserRepository repository;
//
// @Autowired
// public AppUserService( AppUserRepository repository ) {
// this.repository = repository;
// }
//
// @Override
// public UserDetails loadUserByUsername( String username ) throws UsernameNotFoundException {
// AppUserEntity appUserEntity = repository.findByEmail( username );
//
// if( appUserEntity == null ) {
// throw new UsernameNotFoundException( "No user found with username: " + username );
// }
//
// AppUser principal = new AppUser.Builder() //
// .firstName( appUserEntity.getFirstName() ) //
// .id( appUserEntity.getId() ) //
// .lastName( appUserEntity.getLastName() ) //
// .password( appUserEntity.getPassword() ) //
// .role( appUserEntity.getRole() ) //
// .socialSignInProvider( appUserEntity.getSignInProvider() ) //
// .username( appUserEntity.getEmail() ) //
// .build();
//
// return principal;
// }
//
// }
