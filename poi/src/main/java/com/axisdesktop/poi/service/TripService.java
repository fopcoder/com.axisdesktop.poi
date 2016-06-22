package com.axisdesktop.poi.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.axisdesktop.poi.entity.Trip;

public interface TripService {
	Page<Trip> findTrip( Specification<Trip> spec, Pageable page );

	Trip createTrip( Trip trip );

	Trip load( long id );

	Trip updateTrip( Trip trip );

	void deleteTrip( long id );

}
