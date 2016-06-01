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

		for( Filter f : req.getFilters() ) {
			if( f.getField().equals( "active" ) ) {
				Calendar cal = Calendar.getInstance();
				cal.add( Calendar.MINUTE, -Integer.valueOf( env.getRequiredProperty( "crawler.proxy.waitfor" ) ) );

				int maxTries = Integer.valueOf( env.getRequiredProperty( "crawler.proxy.maxtries" ) );
				Predicate status1 = cb.equal( root.get( "statusId" ), 1 );
				Predicate status2 = cb.notEqual( root.get( "statusId" ), 2 );
				Predicate status3 = cb.and( cb.lessThan( root.get( "tries" ), maxTries ), //
						cb.lessThan( root.get( "modified" ), cal ) );
				predicates.add( cb.or( status1, status3 ) );
				predicates.add( status2 );
			}
		}

		return cb.and( predicates.toArray( new Predicate[predicates.size()] ) );
	}

}
