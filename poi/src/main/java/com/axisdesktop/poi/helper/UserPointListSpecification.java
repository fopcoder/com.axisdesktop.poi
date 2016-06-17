package com.axisdesktop.poi.helper;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.axisdesktop.poi.entity.Trip;
import com.axisdesktop.poi.entity.UserPoint;

public class UserPointListSpecification implements Specification<UserPoint> {
	private UserPointListRequestBody data;

	public UserPointListSpecification( UserPointListRequestBody data ) {
		this.data = data;
	}

	@Override
	public Predicate toPredicate( Root<UserPoint> root, CriteriaQuery<?> query, CriteriaBuilder cb ) {
		List<Predicate> predicates = new ArrayList<>();

		if( data.getUserId() != 0 ) {
			predicates.add( cb.equal( root.get( "userId" ), data.getUserId() ) );
		}
		// if( data.getDayId() != 0 ) {
		// predicates.add( cb.equal( root.get( "trip" ), data.getDayId() ) );
		// }

		return cb.and( predicates.toArray( new Predicate[predicates.size()] ) );
	}

}
