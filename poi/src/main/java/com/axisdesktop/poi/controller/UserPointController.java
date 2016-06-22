package com.axisdesktop.poi.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
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
import com.axisdesktop.poi.helper.BaseRequestBody;
import com.axisdesktop.poi.helper.NewUserPointHelper;
import com.axisdesktop.poi.helper.UserPointListSpecification;
import com.axisdesktop.poi.service.CustomUserDetails;
import com.axisdesktop.poi.service.TripService;
import com.axisdesktop.poi.service.UserPoint2TripService;
import com.axisdesktop.poi.service.UserPointService;
import com.axisdesktop.poi.service.UserService;
import com.axisdesktop.utils.HttpUtils;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.PrecisionModel;

@RestController
public class UserPointController {
	// @Autowired
	// private LocationService locService;
	@Autowired
	private UserPointService upointService;
	@Autowired
	private UserPoint2TripService uptService;
	@Autowired
	private UserService userService;
	@Autowired
	private TripService tripService;

	@RequestMapping( "/userpoints" )
	public ResponseEntity<Page<UserPoint>> dayPointList( @RequestBody BaseRequestBody data, Principal user ) {
		if( user == null ) {
			return new ResponseEntity<Page<UserPoint>>( HttpStatus.FORBIDDEN );
		}

		long uid = ( (CustomUserDetails)( (Authentication)user ).getPrincipal() ).getId();

		Pageable page = HttpUtils.createPageable( data );
		Specification<UserPoint> spec = new UserPointListSpecification( uid, data );

		Page<UserPoint> res = upointService.list( spec, page );

		return new ResponseEntity<Page<UserPoint>>( res, HttpStatus.OK );
	}

	@RequestMapping( value = "/userpoint/add" )
	public void addTripPoint( @RequestBody BaseRequestBody data, Principal user ) {
		if( user == null ) {
			return;
		}

		CustomUserDetails ud = (CustomUserDetails)userService.loadUserByUsername( user.getName() );
		GeometryFactory gf = new GeometryFactory( new PrecisionModel(), 4326 );

		UserPoint up = new UserPoint();
		up.setPoint( gf.createPoint( new Coordinate( data.getLongitude(), data.getLatitude() ) ) );
		up.setName( data.getName() );
		up.setPointId( data.getPointId() );
		up.setUserId( ud.getId() );
		upointService.create( up );

		if( data.getTripId() > 0 ) {
			Trip trip = tripService.load( data.getTripId() );

			UserPoint2Trip order = uptService.getLast( trip.getId() );

			UserPoint2Trip up2t = new UserPoint2Trip();
			up2t.setPorder( order == null ? 0 : order.getPorder() + 10 );
			up2t.setTrip( trip );
			up2t.setPoint( up );
			uptService.save( up2t );
		}
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

	// @RequestMapping( value = "/userpoint/create" )
	// public void addTripPoint( @RequestBody RequestBody data, BindingResult bindingResult, Principal user ) {
	//
	// if( bindingResult.hasErrors() ) {
	// return;
	// }
	//
	// if( user == null ) {
	// return;
	// }
	//
	// Trip t = tripService.loadTrip( data.tripId );
	//
	// CustomUserDetails ud = (CustomUserDetails)userService.loadUserByUsername( user.getName() );
	//
	// UserPoint up = new UserPoint();
	// GeometryFactory gg = new GeometryFactory( new PrecisionModel(), 4326 );
	//
	// up.setPoint( gg.createPoint( new Coordinate( data.longitude, data.latitude ) ) );
	// // up.setName( data.name );
	// up.setName( "point" );
	// // up.setDescription( data.description );
	// up.setUserId( ud.getId() );
	// if( data.pointId > 0 ) {
	// up.setPointId( data.pointId );
	// }
	//
	// up = upointService.create( up );
	//
	// // t.getPoints().add( up );
	// tripService.updateTrip( t );
	//
	// // Point p = new Point( , new PrecisionModel(), 4326 );
	// // System.err.println( user );
	// // System.err.println( arr );
	// return;
	//
	// }
}
