package com.axisdesktop.poi.helper;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.axisdesktop.poi.entity.Trip;

public class TripListSpecification implements Specification<Trip> {
	private Long userId;
	private BaseRequestBody data;
	private boolean isTrip;

	public TripListSpecification( Long userId, BaseRequestBody data ) {
		this( userId, data, true );
	}

	public TripListSpecification( Long userId, BaseRequestBody data, boolean isTrip ) {
		this.userId = userId;
		this.data = data;
		this.isTrip = isTrip;
	}

	@Override
	public Predicate toPredicate( Root<Trip> root, CriteriaQuery<?> query, CriteriaBuilder cb ) {
		List<Predicate> predicates = new ArrayList<>();

		predicates.add( this.isTrip ? cb.isNull( root.get( "parentId" ) ) : cb.isNotNull( root.get( "parentId" ) ) );

		if( userId != null ) {
			Predicate prUser = cb.equal( root.get( "userId" ), userId );
			predicates.add( prUser );
		}

		if( data != null && data.getTripId() > 0 ) {
			Predicate p = cb.equal( root.get( "parentId" ), data.getTripId() );
			predicates.add( p );
		}

		return cb.and( predicates.toArray( new Predicate[predicates.size()] ) );
	}

}
