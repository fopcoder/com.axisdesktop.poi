package com.axisdesktop.poi.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.axisdesktop.poi.entity.Trip;
import com.axisdesktop.poi.entity.UserPoint2Trip;

public interface TripService {
	Page<Trip> list( Specification<Trip> spec, Pageable page );

	Trip create( Trip trip );

	Trip append( Trip trip );

	Trip load( long id );

	Trip load( long id, long userId );

	Trip update( Trip trip );

	void delete( long id );

	Trip getPrev( Trip pt );

	Trip getNext( Trip pt );

	Trip moveUp( Trip pt );

	Trip moveDown( Trip pt );

}
