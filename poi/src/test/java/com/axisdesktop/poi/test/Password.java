package com.axisdesktop.poi.test;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

//@RunWith( SpringJUnit4ClassRunner.class )
// @ContextConfiguration( classes = { AppConf.class, PersistenceConf.class, SocialConf.class, SecurityConf.class } )
public class Password {

	// @Autowired
	// private PasswordEncoder encoder;

	@Test
	public void pwd() {
		PasswordEncoder encoder = new BCryptPasswordEncoder( 10 );
		System.out.println( encoder.encode( "test" ) );
	}
}
