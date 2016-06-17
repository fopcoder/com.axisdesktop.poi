package com.axisdesktop.poi.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.axisdesktop.poi.entity.Trip;
import com.axisdesktop.poi.helper.BaseRequestBody;
import com.axisdesktop.poi.helper.TripListSpecification;
import com.axisdesktop.poi.service.CustomUserDetails;
import com.axisdesktop.poi.service.TripService;
import com.axisdesktop.utils.HttpUtils;

@RestController
public class TripController {
	@Autowired
	private TripService tripService;

	@RequestMapping( value = "/trips", method = RequestMethod.POST )
	public ResponseEntity<Page<Trip>> tripList( @RequestBody BaseRequestBody data, Principal user ) {
		if( user == null ) {
			return new ResponseEntity<Page<Trip>>( HttpStatus.NO_CONTENT );
		}
		else {
			long uid = ( (CustomUserDetails)( (Authentication)user ).getPrincipal() ).getId();

			Pageable page = HttpUtils.createPageable( data );
			Specification<Trip> spec = new TripListSpecification( uid, data );

			Page<Trip> res = tripService.findTrip( spec, page );

			return new ResponseEntity<Page<Trip>>( res, HttpStatus.OK );
		}
	}

}
