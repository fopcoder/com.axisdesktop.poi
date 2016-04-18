// package com.axisdesktop.user;
//
// import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.core.Authentication;
// import org.springframework.security.core.context.SecurityContextHolder;
//
// public class SecurityUtil {
//
// public static void logInUser( AppUser user ) {
// AppUser userDetails = new AppUser.Builder().firstName( user.getFirstName() ).id( user.getId() )
// .lastName( user.getLastName() ).password( user.getPassword() ).role( user.getRole() )
// .socialSignInProvider( user.getSocialSignInProvider() ).username( user.getEmail() ).build();
//
// Authentication authentication = new UsernamePasswordAuthenticationToken( userDetails, null,
// userDetails.getAuthorities() );
// SecurityContextHolder.getContext().setAuthentication( authentication );
// }
// }