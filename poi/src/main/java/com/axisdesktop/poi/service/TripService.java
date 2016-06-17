package com.axisdesktop.poi.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.axisdesktop.poi.entity.Trip;

public interface TripService {
	Page<Trip> findTrip( Specification<Trip> spec, Pageable page );

	Trip createTrip( Trip trip );

	Trip loadTrip( long id );

	Trip updateTrip( Trip trip );

	void deleteTrip( long id );

	List<Trip> findDay( Specification<Trip> spec );

	Trip createDay( Trip day );

	Trip loadDay( long id );

	Trip updateDay( Trip day );

	void deleteDay( long id );
}
