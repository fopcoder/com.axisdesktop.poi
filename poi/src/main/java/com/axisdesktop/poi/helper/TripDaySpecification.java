package com.axisdesktop.poi.helper;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletRequest;

import org.springframework.data.jpa.domain.Specification;

import com.axisdesktop.poi.entity.Trip;

public class TripDaySpecification implements Specification<Trip> {
	private Long userId;
	private HttpServletRequest req;
	private DayListRequestBody data;

	public TripDaySpecification( Long userId, DayListRequestBody data, HttpServletRequest req ) {
		this.userId = userId;
		this.data = data;
		this.req = req;
	}

	@Override
	public Predicate toPredicate( Root<Trip> root, CriteriaQuery<?> query, CriteriaBuilder cb ) {
		List<Predicate> predicates = new ArrayList<Predicate>();

		Predicate prIsTripDay = cb.isNotNull( root.get( "parentId" ) );
		predicates.add( prIsTripDay );

		if( userId != null ) {
			Predicate prUser = cb.equal( root.get( "userId" ), userId );
			predicates.add( prUser );
		}

		if( data != null && data.tripId > 0 ) {
			Predicate p = cb.equal( root.get( "parentId" ), data.tripId );
			predicates.add( p );
		}

		if( req != null ) {

		}

		return cb.and( predicates.toArray( new Predicate[predicates.size()] ) );
	}

}
