package com.axisdesktop.poi.helper;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.axisdesktop.poi.entity.UserPoint;
import com.axisdesktop.poi.entity.UserPoint2Trip;

public class UserPointListSpecification implements Specification<UserPoint> {
	private BaseRequestBody data;
	private Long userId;

	public UserPointListSpecification( Long userId, BaseRequestBody data ) {
		this.data = data;
		this.userId = userId;
	}

	@Override
	public Predicate toPredicate( Root<UserPoint> root, CriteriaQuery<?> query, CriteriaBuilder cb ) {
		List<Predicate> predicates = new ArrayList<>();

		if( userId != null ) {
			Predicate prUser = cb.equal( root.get( "userId" ), userId );
			predicates.add( prUser );
		}

		if( data != null && data.getTripId() > 0 ) {
			// Join<UserPoint, UserPoint2Trip> t = root.join( "point2trip" );
			//
			// Predicate e = cb.equal( t.get( "tripId" ), data.getTripId() );
			// predicates.add( e );
		}

		return cb.and( predicates.toArray( new Predicate[predicates.size()] ) );
	}

}
