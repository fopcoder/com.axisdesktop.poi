package com.axisdesktop.poi.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.axisdesktop.poi.service.LocationService;

@Controller
public class IndexController {
	@Autowired
	private LocationService locService;

	@RequestMapping( "/" )
	public String index() {
		// System.out.println( locService.findAll() );

		return "/index";
	}

	@RequestMapping( "/login" )
	public String login( HttpServletRequest request, Principal currentUser, Model model ) {
		// System.out.println( locService.findAll() );

		return "login";
	}

	@RequestMapping( "/home" )
	public String home( HttpServletRequest request, Principal currentUser, Model model ) {
		// System.out.println( locService.findAll() );

		return "home";
	}

	@RequestMapping( "/porom" )
	public String porom( HttpServletRequest request, Principal currentUser, Model model ) {
		// System.out.println( locService.findAll() );

		return "/index";
	}

}
