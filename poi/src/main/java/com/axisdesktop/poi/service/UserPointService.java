package com.axisdesktop.poi.service;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.axisdesktop.poi.entity.UserPoint;

public interface UserPointService {
	List<UserPoint> list( Specification<UserPoint> spec );

	UserPoint create( UserPoint point );

	UserPoint createTripPoint( UserPoint point );

	UserPoint update( UserPoint point );

	void delete( long id );

}
