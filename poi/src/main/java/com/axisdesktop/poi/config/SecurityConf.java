package com.axisdesktop.poi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.social.security.SpringSocialConfigurer;

import com.axisdesktop.user.AppSocialUserService;
import com.axisdesktop.user.AppUserRepository;
import com.axisdesktop.user.AppUserService;

@Configuration
@EnableWebSecurity
public class SecurityConf extends WebSecurityConfigurerAdapter {
	@Autowired
	private AppUserRepository appUserRepository;

	@Autowired
	public void configureGlobal( AuthenticationManagerBuilder auth ) throws Exception {
		auth.userDetailsService( userDetailsService() ).passwordEncoder( passwordEncoder() );
		// auth.inMemoryAuthentication().withUser( "user" ).password( "password" ).roles( "USER" ) //
		// .and().withUser( "admin" ).password( "password" ).roles( "USER", "ADMIN" );
	}

	@Override
	public void configure( HttpSecurity http ) throws Exception {
		// @formatter:off
		http
        .formLogin()
            .loginPage("/login")
            .loginProcessingUrl("/login/authenticate")
            .failureUrl("/login?param.error=bad_credentials")
            .permitAll()
	    .and()
	        .logout()
	            .logoutUrl("/logout")
	            .deleteCookies("JSESSIONID")
	    .and()
	        .authorizeRequests()
	            .antMatchers("/favicon.ico", "/resources/**", "/static/**").permitAll()
	            .antMatchers("/porom/**","/home/**").authenticated()
	            .antMatchers("/getdata/**").permitAll()
	            //.antMatchers("/porom/**").hasRole( "USER" )
	    .and()
	        .rememberMe()
	    .and()
	        .apply(new SpringSocialConfigurer()
	            .postLoginUrl("/")
	            .alwaysUsePostLoginUrl(true));
		
		
		
//		http
//		.and()
//				// Configures form login
//			.formLogin()
//				.loginPage( "/login" )
//				.loginProcessingUrl( "/login/authenticate" )
//				.failureUrl( "/login?error=bad_credentials" )
//				// Configures the logout function
//			.and()
//				.logout()
//				.deleteCookies( "JSESSIONID" )
//				.logoutUrl( "/logout" )
//				.logoutSuccessUrl( "/login" )
//				// Configures url based authorization
//			.and()
//				.authorizeRequests()
//				// Anyone can access the urls
//				.antMatchers( "/auth/**", "/login", "/signup/**", "/user/register/**" ).permitAll()
//				// The rest of the our application is protected.
//				//.antMatchers( "/**" ).hasRole( "USER" )
//				.antMatchers( "/**" ).permitAll()
//		// Adds the SocialAuthenticationFilter to Spring Security's filter chain.
//			.and()
//				.apply( new SpringSocialConfigurer() );
		// @formatter:on
	}

	@Override
	public void configure( WebSecurity web ) throws Exception {
		web.ignoring().antMatchers( "/getdata/**" );
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder( 10 );
	}

	@Bean
	public SocialUserDetailsService socialUserDetailsService() {
		return new AppSocialUserService( userDetailsService() );
	}

	@Override
	public UserDetailsService userDetailsService() {
		return new AppUserService( appUserRepository );
	}

}
