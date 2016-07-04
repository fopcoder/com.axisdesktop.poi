package com.axisdesktop.poi.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.axisdesktop.poi.entity.Trip;
import com.axisdesktop.poi.entity.UserPoint;

public interface UserPointService {
	Page<UserPoint> list( Specification<UserPoint> spec, Pageable page );

	Page<UserPoint> listTripPoints( long userId, long trupId );

	UserPoint load( long id );

	UserPoint load( long userId, long pointId );

	void moveUp( long pointId, long tripId );

	void moveDown( long pointId, long tripId );

	UserPoint create( UserPoint point );

	UserPoint add( UserPoint point, Trip trip );

	UserPoint update( UserPoint point );

	void remove( long pointId, long tripId );

	void delete( long id );

}
