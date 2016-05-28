package com.axisdesktop.crawler.helper;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.core.env.Environment;
import org.springframework.data.jpa.domain.Specification;

import com.axisdesktop.crawler.entity.CrawlerProxy;

import ch.ralscha.extdirectspring.bean.ExtDirectStoreReadRequest;
import ch.ralscha.extdirectspring.filter.Filter;

public class ProxySpecification implements Specification<CrawlerProxy> {
	private ExtDirectStoreReadRequest req;
	private Environment env;

	public ProxySpecification( ExtDirectStoreReadRequest req, Environment env ) {
		this.req = req;
		this.env = env;
	}

	@Override
	public Predicate toPredicate( Root<CrawlerProxy> root, CriteriaQuery<?> query, CriteriaBuilder cb ) {
		List<Predicate> predicates = new ArrayList<Predicate>();

		// System.err.println( Arrays.deepToString( req.getFilters().toArray() ) );
		for( Filter f : req.getFilters() ) {
			System.err.println( f.getField() );
			if( f.getField().equals( "active" ) ) {
				Calendar cal = Calendar.getInstance();
				cal.add( Calendar.MINUTE, -Integer.valueOf( env.getRequiredProperty( "crawler.proxy.waitfor" ) ) );

				predicates.add( cb.lessThan( root.get( "tries" ),
						Integer.valueOf( env.getRequiredProperty( "crawler.proxy.maxtries" ) ) ) );
				predicates.add( cb.lessThan( root.get( "modified" ), cal ) );

			}
			// // Ma p m = (Map)f;
			// System.err.println( f );
		}

		Filter f = req.getFirstFilterForField( "active" );

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
