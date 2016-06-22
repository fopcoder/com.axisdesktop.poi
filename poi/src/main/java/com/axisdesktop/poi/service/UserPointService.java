package com.axisdesktop.poi.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.axisdesktop.poi.entity.UserPoint;

public interface UserPointService {
	List<UserPoint> list( Specification<UserPoint> spec );

	Page<UserPoint> list( Specification<UserPoint> spec, Pageable page );

	UserPoint create( UserPoint point );

	UserPoint addPoint( UserPoint point );

	UserPoint update( UserPoint point );

	void delete( long id );

}
