package com.axisdesktop.poi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

}
