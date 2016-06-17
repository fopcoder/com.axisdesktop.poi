package com.axisdesktop.poi.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.axisdesktop.poi.entity.Trip;
import com.axisdesktop.poi.entity.UserPoint;
import com.axisdesktop.poi.entity.UserPoint2Trip;
import com.axisdesktop.poi.helper.BBoxHelper;
import com.axisdesktop.poi.helper.BaseRequestBody;
import com.axisdesktop.poi.helper.NewUserPointHelper;
import com.axisdesktop.poi.helper.TripPointRequestBody;
import com.axisdesktop.poi.helper.UserPointListSpecification;
import com.axisdesktop.poi.service.CustomUserDetails;
import com.axisdesktop.poi.service.LocationService;
import com.axisdesktop.poi.service.TripService;
import com.axisdesktop.poi.service.UserPointService;
import com.axisdesktop.poi.service.UserService;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.PrecisionModel;

@RestController
public class UserPointController {
	@Autowired
	private LocationService locService;
	@Autowired
	private UserPointService upointService;
	@Autowired
	private UserService userService;
	@Autowired
	private TripService tripService;

	@RequestMapping( "/userpoints" )
	public ResponseEntity<List<UserPoint>> dayPointList( @RequestBody BaseRequestBody data, Principal user ) {
		if( user == null ) {
			return new ResponseEntity<List<UserPoint>>( HttpStatus.FORBIDDEN );
		}

		// CustomUserDetails ud = (CustomUserDetails)userService.loadUserByUsername( user.getName() );
		Trip t = tripService.loadTrip( data.getTripId() );
		// List<UserPoint> res = t.getPoint2trip().stream().map( i -> {
		// System.err.println( i.getPoint() );
		// return i.getPoint();
		// } ).collect( Collectors.toList() );
		// System.err.println( res );

		List<UserPoint> res = new ArrayList<>();

		for( UserPoint2Trip up : t.getPoint2trip() ) {
			res.add( up.getPoint() );
			System.err.println( up.getPoint().getName() );
		}
		System.err.println( res );
		// res = upointService.list( new UserPointListSpecification( data.g ) );

		return new ResponseEntity<List<UserPoint>>( res, HttpStatus.OK );

	}

	@RequestMapping( value = { "/point999/list", "/point" } )
	public List<String[]> pointList( @RequestBody BBoxHelper arr ) {

		List<String[]> res = locService.findPointsInBoundingBox( arr.south, arr.west, arr.north, arr.east );

		return res;
	}

	@RequestMapping( value = "/point999/create", method = RequestMethod.POST )
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

	@RequestMapping( value = "/userpoint/create" )
	public void addTripPoint( @RequestBody TripPointRequestBody data, BindingResult bindingResult, Principal user ) {

		if( bindingResult.hasErrors() ) {
			return;
		}

		if( user == null ) {
			return;
		}

		Trip t = tripService.loadTrip( data.tripId );

		CustomUserDetails ud = (CustomUserDetails)userService.loadUserByUsername( user.getName() );

		UserPoint up = new UserPoint();
		GeometryFactory gg = new GeometryFactory( new PrecisionModel(), 4326 );

		up.setPoint( gg.createPoint( new Coordinate( data.longitude, data.latitude ) ) );
		// up.setName( data.name );
		up.setName( "point" );
		// up.setDescription( data.description );
		up.setUserId( ud.getId() );
		if( data.pointId > 0 ) {
			up.setPointId( data.pointId );
		}

		up = upointService.create( up );

		// t.getPoints().add( up );
		tripService.updateTrip( t );

		// Point p = new Point( , new PrecisionModel(), 4326 );
		// System.err.println( user );
		// System.err.println( arr );
		return;

	}
}
