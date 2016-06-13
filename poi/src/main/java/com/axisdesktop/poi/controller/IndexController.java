package com.axisdesktop.poi.controller;

import java.security.Principal;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.axisdesktop.poi.service.LocationService;

@Controller
public class IndexController {
	@Autowired
	private LocationService locService;

	@RequestMapping( "/" )
	public String index( Principal user, Model model ) {
		if( user != null ) {
			model.addAttribute( "username", user.getName() );
		}
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		return "/index";
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

	// @RequestMapping( value = "/getdata1", produces = "application/json" )
	// @ResponseBody
	// public List<String[]> data( @RequestBody BBoxHelper arr ) {
	// System.out.println( arr );
	//
	// // double south = 0, west = 0, north = 0, east = 0;
	//
	// List<String[]> res = locService.findPointsInBoundingBox( arr.south, arr.west, arr.north, arr.east );
	//
	// // System.out.println( east );
	// // System.out.println( south );
	// // System.out.println( north );
	// // System.out.println( west );
	//
	// // String[][] str = { { "1", "23.555", "sssssss" }, { "2", "43.999", "aaaaa" } };
	// // List<Location> l = locService.findAll();
	//
	// return res;
	// }

	@RequestMapping( value = "/poidata/{id}", produces = "application/json" )
	@ResponseBody
	public Map<String, String> poiData( @PathVariable long id ) {
		locService.getPoiData( id );
		return null;
	}

}
