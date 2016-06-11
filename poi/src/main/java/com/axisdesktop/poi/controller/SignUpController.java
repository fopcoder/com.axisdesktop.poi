package com.axisdesktop.poi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;

@Controller
public class SignUpController {

	// @Autowired
	// ProviderSignInUtils providerSignInUtils;

	@RequestMapping( value = "/signup", method = RequestMethod.GET )
	public String redirectRequestToRegistrationPage( WebRequest request ) {
		// Connection<?> connection = providerSignInUtils.getConnectionFromSession( request );
		// System.err.println( connection );
		// Connection<?> connection = ProviderSignInUtils.getConnectionFromSession( request );

		return "redirect:/user/register";
	}
}