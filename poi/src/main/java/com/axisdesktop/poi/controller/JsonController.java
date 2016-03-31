package com.axisdesktop.poi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.axisdesktop.poi.service.LocationService;

@RestController
public class JsonController {
	@Autowired
	private LocationService locService;

	@RequestMapping( value = "/getdata", method = RequestMethod.POST )
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
