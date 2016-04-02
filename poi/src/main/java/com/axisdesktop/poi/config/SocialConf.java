package com.axisdesktop.poi.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.UserIdSource;
import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurer;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.connect.web.ConnectController;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.google.connect.GoogleConnectionFactory;
import org.springframework.social.security.AuthenticationNameUserIdSource;
import org.springframework.social.twitter.connect.TwitterConnectionFactory;

@Configuration
@EnableSocial
public class SocialConf implements SocialConfigurer {
	@Autowired
	private DataSource dataSource;

	@Override
	public void addConnectionFactories( ConnectionFactoryConfigurer cfConfig, Environment env ) {
		cfConfig.addConnectionFactory( new TwitterConnectionFactory( env.getProperty( "poiroute.twitter.key" ),
				env.getProperty( "poiroute.twitter.secret" ) ) );
		cfConfig.addConnectionFactory( new FacebookConnectionFactory( env.getProperty( "poiroute.facebook.key" ),
				env.getProperty( "poiroute.facebook.secret" ) ) );
		cfConfig.addConnectionFactory( new GoogleConnectionFactory( env.getProperty( "poiroute.google.key" ),
				env.getProperty( "poiroute.google.secret" ) ) );
	}

	@Override
	public UserIdSource getUserIdSource() {
		return new AuthenticationNameUserIdSource();
	}

	@Override
	public UsersConnectionRepository getUsersConnectionRepository( ConnectionFactoryLocator connectionFactoryLocator ) {
		JdbcUsersConnectionRepository repo = new JdbcUsersConnectionRepository( dataSource, connectionFactoryLocator,
				Encryptors.noOpText() );
		repo.setTablePrefix( "poi." );
		return repo;
	}

	@Bean
	public ConnectController connectController( ConnectionFactoryLocator connectionFactoryLocator,
			ConnectionRepository connectionRepository ) {
		ConnectController controller = new ConnectController( connectionFactoryLocator, connectionRepository );
		return controller;

	}
}
