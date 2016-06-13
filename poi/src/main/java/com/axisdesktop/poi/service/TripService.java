package com.axisdesktop.poi.service;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.axisdesktop.poi.entity.Trip;

public interface TripService {
	List<Trip> findTrip( Specification<Trip> spec );

	Trip createTrip( Trip trip );

	Trip getTrip( long id );

	Trip updateTrip( Trip trip );

	void deleteTrip( long id );

	List<Trip> findDay();

	Trip createDay( Trip day );

	Trip getDay( long id );

	Trip updateDay( Trip day );

	void deleteDay( long id );
}
