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

public class TripSpecification implements Specification<Trip> {
	private Long userId;
	private HttpServletRequest req;

	public TripSpecification( Long userId, HttpServletRequest req ) {
		this.userId = userId;
		this.req = req;
	}

	@Override
	public Predicate toPredicate( Root<Trip> root, CriteriaQuery<?> query, CriteriaBuilder cb ) {
		List<Predicate> predicates = new ArrayList<Predicate>();

		Predicate prIsTrip = cb.isNull( root.get( "parentId" ) );
		predicates.add( prIsTrip );

		if( userId != null ) {
			Predicate prUser = cb.equal( root.get( "userId" ), userId );
			predicates.add( prUser );
		}

		if( req != null ) {

		}

		return cb.and( predicates.toArray( new Predicate[predicates.size()] ) );
	}

}
