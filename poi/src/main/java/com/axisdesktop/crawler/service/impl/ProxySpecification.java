package com.axisdesktop.crawler.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.axisdesktop.crawler.entity.CrawlerProxy;

import ch.ralscha.extdirectspring.bean.ExtDirectStoreReadRequest;

public class ProxySpecification implements Specification<CrawlerProxy> {
	ExtDirectStoreReadRequest req;

	public ProxySpecification( ExtDirectStoreReadRequest req ) {
		this.req = req;
	}

	@Override
	public Predicate toPredicate( Root<CrawlerProxy> root, CriteriaQuery<?> query, CriteriaBuilder cb ) {
		List<Predicate> predicates = new ArrayList<Predicate>();

		System.err.println( Arrays.deepToString( req.getFilters().toArray() ) );
		for( Object f : req.getFilters().toArray() ) {
			Map m = (Map)f;
			System.err.println( m.get( "property" ) );
		}
		// req.getFirstFilterForField( "property" );
		// if( req.getFirstFilterForField( "koko" ). ) {
		//
		// }
		// StringFilter nameFilter = req.getFirstFilterForField( "koko" );
		// System.err.println( nameFilter );
		// for( StringFilter f : req.getFilters() ) {
		// if( f.getField().equals( "" ) ) {
		// System.err.println( f );
		// }
		// if (StringUtils.isNotBlank(example.getLastName())) {
		// predicates.add(cb.like(cb.lower(root.get(Person_.lastName)), example.getLastName().toLowerCase() + "%"));
		// }
		//
		// if (StringUtils.isNotBlank(example.getFirstName())) {
		// predicates.add(cb.like(cb.lower(root.get(Person_.firstName)), example.getFirstName().toLowerCase() +
		// "%"));
		// }
		//
		// if (example.getEmployed() != null) {
		// predicates.add(cb.equal(root.get(Person_.employed), example.getEmployed()));
		// }
		//
		// if (example.getDob() != null) {
		// predicates.add(cb.equal(root.get(Person_.dob), example.getDob()));
		// }

		// }

		return cb.and( predicates.toArray( new Predicate[predicates.size()] ) );
	}

}
