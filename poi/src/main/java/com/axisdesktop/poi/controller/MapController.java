package com.axisdesktop.poi.controller;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.axisdesktop.poi.entity.UserPoint;
import com.axisdesktop.poi.helper.BBoxHelper;
import com.axisdesktop.poi.helper.LocationInfo;
import com.axisdesktop.poi.helper.NewUserPointHelper;
import com.axisdesktop.poi.repository.LocationRepository;
import com.axisdesktop.poi.service.CustomUserDetails;
import com.axisdesktop.poi.service.LocationService;
import com.axisdesktop.poi.service.TripService;
import com.axisdesktop.poi.service.UserPointService;
import com.axisdesktop.poi.service.UserService;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.PrecisionModel;

@RestController
public class MapController {
	@Autowired
	private LocationService locService;
	@Autowired
	private UserPointService upointService;
	@Autowired
	private UserService userService;
	@Autowired
	private TripService tripService;
	@Autowired
	private LocationRepository locRepo;

	@RequestMapping( value = { "/point/list", "/point" } )
	public List<String[]> pointList( @RequestBody BBoxHelper arr ) {

		List<String[]> res = locService.findPointsInBoundingBox( arr.south, arr.west, arr.north, arr.east );

		return res;
	}

	@RequestMapping( value = "/point/info/{id}" )
	public ResponseEntity<LocationInfo> loadPointInfo( @PathVariable long id ) {
		LocationInfo res = locRepo.loadLocationInfo( id );

		return new ResponseEntity<LocationInfo>( res, HttpStatus.OK );
	}

	@RequestMapping( value = "/point/create", method = RequestMethod.POST )
	public UserPoint createUserPoint( @Valid @RequestBody NewUserPointHelper arr, Principal user,
			BindingResult bindingResult, Authentication auth ) {

		if( bindingResult.hasErrors() ) {
			return null;
		}

		if( user == null ) {
			return null;
		}

		CustomUserDetails ud = (CustomUserDetails)userService.loadUserByUsername( user.getName() );

		UserPoint up = new UserPoint();
		GeometryFactory gg = new GeometryFactory( new PrecisionModel(), 4326 );

		up.setPoint( gg.createPoint( new Coordinate( arr.longitude, arr.latitude ) ) );
		up.setName( arr.name );
		up.setDescription( arr.description );
		up.setUserId( ud.getId() );

		up = upointService.create( up );

		// Point p = new Point( , new PrecisionModel(), 4326 );
		// System.err.println( user );
		// System.err.println( arr );
		return null;
	}

	// @RequestMapping( value = "/trip/day/list" )
	// public ResponseEntity<List<Trip>> dayList( @RequestBody BaseRequestBody data, HttpServletRequest req,
	// Principal user ) {
	//
	// if( user == null ) {
	// return new ResponseEntity<List<Trip>>( HttpStatus.NO_CONTENT );
	// }
	// else {
	// CustomUserDetails ud = (CustomUserDetails)userService.loadUserByUsername( user.getName() );
	//
	// Specification<Trip> spec = new TripListSpecification( ud.getId(), data, false );
	// List<Trip> res = tripService.findDay( spec );
	//
	// return new ResponseEntity<List<Trip>>( res, HttpStatus.OK );
	// }
	// }

}
