package com.axisdesktop.poi.controller;

import java.security.Principal;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.axisdesktop.poi.service.LocationService;
import com.fasterxml.jackson.core.JsonProcessingException;

import ch.ralscha.extdirectspring.util.ExtDirectSpringUtil;

@Controller
public class IndexController {
	@Autowired
	private LocationService locService;

	@Autowired
	private ApplicationContext ctx;

	@RequestMapping( "/" )
	public String index() throws JsonProcessingException {
		// System.out.println( locService.findAll() );

		String extDirectConfig = ExtDirectSpringUtil.generateApiString( ctx );
		String userDir = System.getProperty( "user.dir" );

		System.err.println( extDirectConfig );

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
	public List<String[]> data( @RequestBody BBoxHelper arr ) {
		System.out.println( arr );

		// double south = 0, west = 0, north = 0, east = 0;

		List<String[]> res = locService.findPointsInBoundingBox( arr.south, arr.west, arr.north, arr.east );

		// System.out.println( east );
		// System.out.println( south );
		// System.out.println( north );
		// System.out.println( west );

		// String[][] str = { { "1", "23.555", "sssssss" }, { "2", "43.999", "aaaaa" } };
		// List<Location> l = locService.findAll();

		return res;
	}

	@RequestMapping( value = "/poidata/{id}", produces = "application/json" )
	@ResponseBody
	public Map<String, String> poiData( @PathVariable long id ) {
		locService.getPoiData( id );
		return null;
	}

}
