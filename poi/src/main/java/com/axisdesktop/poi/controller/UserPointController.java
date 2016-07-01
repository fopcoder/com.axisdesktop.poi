package com.axisdesktop.poi.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.axisdesktop.poi.entity.Trip;
import com.axisdesktop.poi.entity.UserPoint;
import com.axisdesktop.poi.helper.BaseRequestBody;
import com.axisdesktop.poi.helper.LocationInfo;
import com.axisdesktop.poi.repository.LocationRepository;
import com.axisdesktop.poi.service.CustomUserDetails;
import com.axisdesktop.poi.service.TripService;
import com.axisdesktop.poi.service.UserPointService;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.PrecisionModel;

@RestController
public class UserPointController {

	@Autowired
	private UserPointService upointService;

	@Autowired
	private TripService tripService;

	@Autowired
	private LocationRepository locRepo;

	@RequestMapping( "/userpoint/trippoints" )
	public ResponseEntity<Page<UserPoint>> dayPointList( @RequestBody BaseRequestBody data, Principal user ) {
		if( user == null ) {
			return new ResponseEntity<Page<UserPoint>>( HttpStatus.FORBIDDEN );
		}

		long uid = ( (CustomUserDetails)( (Authentication)user ).getPrincipal() ).getId();

		// Pageable page = HttpUtils.createPageable( data );
		// Specification<UserPoint> spec = new UserPointListSpecification( uid, data );

		// Page<UserPoint> res = upointService.list( spec, page );

		Page<UserPoint> res = upointService.listTripPoints( uid, data.getTripId() );

		return new ResponseEntity<Page<UserPoint>>( res, HttpStatus.OK );
	}

	@RequestMapping( value = "/userpoint/info/{id}" )
	public ResponseEntity<LocationInfo> loadPointInfo( @PathVariable( "id" ) long pointId, Principal user ) {
		if( user == null ) {
			return null;
		}

		long uid = ( (CustomUserDetails)( (Authentication)user ).getPrincipal() ).getId();

		UserPoint up = upointService.load( pointId );

		if( up.getUserId() != uid ) {
			return null;
		}

		LocationInfo info;

		if( up.getPointId() != null ) {
			info = locRepo.loadLocationInfo( up.getPointId() );
		}
		else {
			info = new LocationInfo( up.getId(), up.getLatitude(), up.getLongitude(), up.getName(),
					up.getDescription() );
		}

		return new ResponseEntity<LocationInfo>( info, HttpStatus.OK );
	}

	@RequestMapping( value = "/userpoint/add" )
	public void addTripPoint( @RequestBody BaseRequestBody data, Principal user ) {
		if( user == null ) {
			return;
		}

		long uid = ( (CustomUserDetails)( (Authentication)user ).getPrincipal() ).getId();
		GeometryFactory gf = new GeometryFactory( new PrecisionModel(), 4326 );

		UserPoint up;

		if( data.isUserPoint() ) {
			up = upointService.load( data.getPointId() );
			if( up.getUserId() != uid ) return;
		}
		else {
			up = new UserPoint();
			up.setPoint( gf.createPoint( new Coordinate( data.getLongitude(), data.getLatitude() ) ) );
			up.setName( data.getName() );
			up.setDescription( data.getDescription() );
			if( data.getPointId() > 0 ) up.setPointId( data.getPointId() );
			up.setUserId( uid );
		}

		Trip trip = tripService.load( data.getTripId(), uid );

		upointService.add( up, trip );
	}

	@RequestMapping( value = "/userpoint/remove" )
	public void removeTripPoint( @RequestBody BaseRequestBody data, Principal user ) {
		if( user == null ) {
			return;
		}

		upointService.remove( data.getPointId(), data.getTripId() );
	}

	@RequestMapping( value = "/userpoint/kml/{tripId}", produces = "text/xml;charset=UTF-8" )
	public String exportKml( @PathVariable( "tripId" ) long tripId, Principal user ) {
		if( user == null ) {
			return null;
		}

		long uid = ( (CustomUserDetails)( (Authentication)user ).getPrincipal() ).getId();

		Trip trip = tripService.load( tripId, uid );

		if( trip == null ) {
			return null;
		}

		Page<UserPoint> res = upointService.listTripPoints( uid, tripId );

		StringBuilder sb = new StringBuilder( 10 );
		sb.append( String.format( "<?xml version='1.0' encoding='UTF-8'?><kml xmlns='http://www.opengis.net/kml/2.2'>"
				+ "<Document><name>%s</name>", trip.getName() ) );

		for( UserPoint up : res ) {
			sb.append( String.format(
					"<Placemark><name>%s</name><Point><coordinates>%s,%s,0.0</coordinates></Point></Placemark>",
					up.getName(), up.getLongitude(), up.getLatitude() ) );
		}

		sb.append( "</Document></kml>" );

		return sb.toString();
	}

	@RequestMapping( value = "/userpoint/txt/{tripId}", produces = "text/html;charset=UTF-8" )
	public String exportTxt( @PathVariable( "tripId" ) long tripId, Principal user ) {
		if( user == null ) {
			return null;
		}

		long uid = ( (CustomUserDetails)( (Authentication)user ).getPrincipal() ).getId();

		Trip trip = tripService.load( tripId, uid );

		if( trip == null ) {
			return null;
		}

		Page<UserPoint> res = upointService.listTripPoints( uid, tripId );

		StringBuilder sb = new StringBuilder( 10 );
		sb.append( String.format( "<html><body><H1>%s</H1>", trip.getName() ) );

		for( UserPoint up : res ) {
			LocationInfo info;

			if( up.getPointId() != null ) {
				info = locRepo.loadLocationInfo( up.getPointId() );
			}
			else {
				info = new LocationInfo( up.getId(), up.getLatitude(), up.getLongitude(), up.getName(),
						up.getDescription() );
			}

			sb.append( String.format( "<p><b>%s</b><br>", info.getName() ) );

			if( info.getDescription() != null && !info.getDescription().equals( "" ) )
				sb.append( String.format( "%s<br>", info.getDescription() ) );

			if( info.getAddress() != null && !info.getAddress().equals( "" ) )
				sb.append( String.format( "%s<br>", info.getAddress() ) );

			if( info.getLink() != null && !info.getLink().equals( "" ) )
				sb.append( String.format( "<a href='%s' target='_blank'>%s</a><br>", info.getLink(), info.getLink() ) );

			sb.append( String.format(
					"<a href='https://www.google.com.ua/maps/@%s,%s,16z?hl=uk' target='_blank'>координаты</a></p>",
					info.getLatitude(), info.getLongitude() ) );
		}

		sb.append( "</body></html>" );

		return sb.toString();
	}

	////
	// @RequestMapping( value = "/point999/create", method = RequestMethod.POST )
	// public UserPoint createUserPoint( @Valid @RequestBody NewUserPointHelper arr, Principal user,
	// BindingResult bindingResult, Authentication auth ) {
	//
	// if( bindingResult.hasErrors() ) {
	// return null;
	// }
	//
	// if( user == null ) {
	// return null;
	// }
	//
	// CustomUserDetails ud = (CustomUserDetails)userService.loadUserByUsername( user.getName() );
	//
	// UserPoint up = new UserPoint();
	// GeometryFactory gg = new GeometryFactory( new PrecisionModel(), 4326 );
	//
	// up.setPoint( gg.createPoint( new Coordinate( arr.longitude, arr.latitude ) ) );
	// up.setName( arr.name );
	// up.setDescription( arr.description );
	// up.setUserId( ud.getId() );
	//
	// up = upointService.create( up );
	//
	// // Point p = new Point( , new PrecisionModel(), 4326 );
	// // System.err.println( user );
	// // System.err.println( arr );
	// return null;
	// }
	//

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
