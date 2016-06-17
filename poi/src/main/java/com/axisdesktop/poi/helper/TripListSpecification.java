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

	public TripListSpecification( Long userId, BaseRequestBody data ) {
		this.userId = userId;
		this.data = data;
	}

	@Override
	public Predicate toPredicate( Root<Trip> root, CriteriaQuery<?> query, CriteriaBuilder cb ) {
		List<Predicate> predicates = new ArrayList<>();

		Predicate prIsTrip = cb.isNull( root.get( "parentId" ) );
		predicates.add( prIsTrip );

		if( userId != null ) {
			Predicate prUser = cb.equal( root.get( "userId" ), userId );
			predicates.add( prUser );
		}

		// if( req != null ) {
		//
		// }

		return cb.and( predicates.toArray( new Predicate[predicates.size()] ) );
	}

}
