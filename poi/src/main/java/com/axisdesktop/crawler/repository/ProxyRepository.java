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
	@Query( "SELECT p FROM CrawlerProxy p WHERE statusId = 1 OR ( statusId = 3 AND modified < :waitFor AND tries < :maxTries ) ORDER BY statusId, RANDOM()" )
	List<CrawlerProxy> findRandomActiveProxy( @Param( "waitFor" ) Calendar date, @Param( "maxTries" ) int maxTries,
			Pageable pageable );

	@Query( "SELECT COUNT(*) > 0 FROM CrawlerProxy p WHERE host LIKE :host AND port = :port" )
	boolean isExistByHostAndPort( @Param( "host" ) String host, @Param( "port" ) int port );
}
