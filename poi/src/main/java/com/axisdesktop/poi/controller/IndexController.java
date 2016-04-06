package com.axisdesktop.poi.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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

	// @RequestMapping( "/login" )
	// public String login( HttpServletRequest request, Principal currentUser, Model model ) {
	// // System.out.println( locService.findAll() );
	//
	// return "login";
	// }

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

	@RequestMapping( value = "/getdata", produces = "application/json" )
	@ResponseBody
	public List<String[]> data( double south, double west, double north, double east ) {

		List<String[]> res = locService.findPointsInBoundingBox( south, west, north, east );

		System.out.println( east );
		System.out.println( south );
		System.out.println( north );
		System.out.println( west );

		// String[][] str = { { "1", "23.555", "sssssss" }, { "2", "43.999", "aaaaa" } };
		// List<Location> l = locService.findAll();

		return res;
	}

}
