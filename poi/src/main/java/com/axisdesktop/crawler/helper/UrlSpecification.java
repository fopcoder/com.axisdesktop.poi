package com.axisdesktop.crawler.helper;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.core.env.Environment;
import org.springframework.data.jpa.domain.Specification;

import com.axisdesktop.crawler.entity.ProviderUrl;

import ch.ralscha.extdirectspring.bean.ExtDirectStoreReadRequest;
import ch.ralscha.extdirectspring.filter.NumericFilter;

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

		NumericFilter typeIdFilter = req.getFirstFilterForField( "typeId" );
		if( typeIdFilter != null ) {
			predicates.add( cb.equal( root.get( "typeId" ), typeIdFilter.getValue() ) );
		}

		NumericFilter statusIdFilter = req.getFirstFilterForField( "statusId" );
		if( statusIdFilter != null ) {
			predicates.add( cb.equal( root.get( "statusId" ), statusIdFilter.getValue() ) );
		}

		return cb.and( predicates.toArray( new Predicate[predicates.size()] ) );
	}

}
