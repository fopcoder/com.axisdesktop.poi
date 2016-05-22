package com.axisdesktop.crawler.repository;

import java.util.Calendar;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.axisdesktop.crawler.entity.CrawlerProxy;

public interface ProxyRepository extends JpaRepository<CrawlerProxy, Integer>, JpaSpecificationExecutor<CrawlerProxy> {
	@Query( name = "CrawlerProxy.findActiveOrderByRandom" )
	List<CrawlerProxy> getRandomActiveProxy( @Param( "waitFor" ) Calendar date, @Param( "maxTries" ) int maxTries,
			Pageable pageable );
}
