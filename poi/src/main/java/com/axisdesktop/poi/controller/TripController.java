package com.axisdesktop.poi.controller;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

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
import com.axisdesktop.poi.entity.UserPoint;
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
			return new ResponseEntity<Page<Trip>>( HttpStatus.FORBIDDEN );
		}
		else {
			long uid = ( (CustomUserDetails)( (Authentication)user ).getPrincipal() ).getId();

			Pageable page = HttpUtils.createPageable( data );
			Specification<Trip> spec = new TripListSpecification( uid, data );

			Page<Trip> res = tripService.list( spec, page );

			return new ResponseEntity<Page<Trip>>( res, HttpStatus.OK );
		}
	}

	@RequestMapping( value = "/trip/days", method = RequestMethod.POST )
	public ResponseEntity<Page<Trip>> tripDaysList( @RequestBody BaseRequestBody data, Principal user ) {
		if( user == null ) {
			return new ResponseEntity<Page<Trip>>( HttpStatus.FORBIDDEN );
		}
		else {
			long uid = ( (CustomUserDetails)( (Authentication)user ).getPrincipal() ).getId();

			Pageable page = HttpUtils.createPageable( data );
			Specification<Trip> spec = new TripListSpecification( uid, data, false );

			Page<Trip> res = tripService.list( spec, page );

			return new ResponseEntity<Page<Trip>>( res, HttpStatus.OK );
		}
	}

	@RequestMapping( value = "/trip/day/points", method = RequestMethod.POST )
	public ResponseEntity<List<UserPoint>> tripPointsList( @RequestBody BaseRequestBody data, Principal user ) {
		if( user == null ) {
			return new ResponseEntity<List<UserPoint>>( HttpStatus.FORBIDDEN );
		}

		Trip t = tripService.load( data.getTripId() );
		List<UserPoint> res = t.getPoint2trip().stream().map( i -> {
			return i.getPoint();
		} ).collect( Collectors.toList() );

		return new ResponseEntity<List<UserPoint>>( res, HttpStatus.OK );

	}

	@RequestMapping( value = "/trip/load", method = RequestMethod.POST )
	public ResponseEntity<Trip> loadTrip( @RequestBody BaseRequestBody data, Principal user ) {
		if( user == null ) {
			return new ResponseEntity<Trip>( HttpStatus.FORBIDDEN );
		}

		long uid = ( (CustomUserDetails)( (Authentication)user ).getPrincipal() ).getId();
		Trip trip = tripService.load( data.getTripId(), uid );

		return new ResponseEntity<Trip>( trip, HttpStatus.OK );
	}

	@RequestMapping( value = "/trip/create", method = RequestMethod.POST )
	public void createTrip( @RequestBody BaseRequestBody data, Principal user ) {
		if( user == null ) {
			return;
		}

		long uid = ( (CustomUserDetails)( (Authentication)user ).getPrincipal() ).getId();

		Trip trip = new Trip();
		trip.setName( data.getName() );
		trip.setUserId( uid );
		if( data.getTripId() > 0 ) trip.setParentId( data.getTripId() );

		tripService.append( trip );
	}

	@RequestMapping( value = "/trip/update", method = RequestMethod.POST )
	public void updateTrip( @RequestBody BaseRequestBody data, Principal user ) {
		if( user == null ) {
			return;
		}

		long uid = ( (CustomUserDetails)( (Authentication)user ).getPrincipal() ).getId();

		Trip trip = tripService.load( data.getTripId(), uid );

		if( trip != null ) {
			trip.setName( data.getName() );
			tripService.update( trip );
		}
	}

	@RequestMapping( value = "/trip/delete", method = RequestMethod.POST )
	public void deleteTrip( @RequestBody BaseRequestBody data, Principal user ) {
		if( user == null ) {
			return;
		}

		long uid = ( (CustomUserDetails)( (Authentication)user ).getPrincipal() ).getId();

		Trip trip = tripService.load( data.getTripId(), uid );

		if( trip != null ) {
			tripService.delete( trip.getId() );
		}
	}

	@RequestMapping( value = "/trip/moveUp" )
	public void moveUp( @RequestBody BaseRequestBody data, Principal user ) {
		if( user == null ) {
			return;
		}

		long uid = ( (CustomUserDetails)( (Authentication)user ).getPrincipal() ).getId();
		Trip trip = tripService.load( data.getTripId(), uid );

		if( trip != null ) {
			tripService.moveUp( trip );
		}
	}

	@RequestMapping( value = "/trip/moveDown" )
	public void moveDown( @RequestBody BaseRequestBody data, Principal user ) {
		if( user == null ) {
			return;
		}

		long uid = ( (CustomUserDetails)( (Authentication)user ).getPrincipal() ).getId();
		Trip trip = tripService.load( data.getTripId(), uid );

		if( trip != null ) {
			tripService.moveDown( trip );
		}
	}

}
