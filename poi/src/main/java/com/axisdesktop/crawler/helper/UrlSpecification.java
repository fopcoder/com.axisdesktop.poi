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

import com.axisdesktop.crawler.entity.ProviderUrl;

import ch.ralscha.extdirectspring.bean.ExtDirectStoreReadRequest;
import ch.ralscha.extdirectspring.filter.Filter;

public class UrlSpecification implements Specification<ProviderUrl> {
	private ExtDirectStoreReadRequest req;
	private Environment env;

	public UrlSpecification( ExtDirectStoreReadRequest req, Environment env ) {
		this.req = req;
		this.env = env;
	}

	@Override
	public Predicate toPredicate( Root<ProviderUrl> root, CriteriaQuery<?> query, CriteriaBuilder cb ) {
		List<Predicate> predicates = new ArrayList<Predicate>();

		for( Filter f : req.getFilters() ) {
			if( f.getField().equals( "active" ) ) {
				Calendar cal = Calendar.getInstance();
				cal.add( Calendar.MINUTE, -Integer.valueOf( env.getRequiredProperty( "crawler.proxy.waitfor" ) ) );

				predicates.add( cb.lessThan( root.get( "tries" ),
						Integer.valueOf( env.getRequiredProperty( "crawler.proxy.maxtries" ) ) ) );
				predicates.add( cb.lessThan( root.get( "modified" ), cal ) );

			}
		}

		return cb.and( predicates.toArray( new Predicate[predicates.size()] ) );
	}

}
