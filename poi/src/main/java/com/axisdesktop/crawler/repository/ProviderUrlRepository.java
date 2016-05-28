package com.axisdesktop.crawler.repository;

import java.util.Calendar;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.axisdesktop.crawler.entity.ProviderUrl;

public interface ProviderUrlRepository extends JpaRepository<ProviderUrl, Long>, JpaSpecificationExecutor<ProviderUrl> {
	@Query( name = "ProviderUrl.findActiveFeedUrl" )
	List<ProviderUrl> findActiveFeedUrl( @Param( "providerId" ) int providerId, @Param( "waitFor" ) Calendar waitFor,
			@Param( "nextTime" ) Calendar nextTime, @Param( "maxTries" ) int maxTries );

	List<ProviderUrl> findByProviderIdAndUrl( int providerId, String url, Pageable page );

	@Query( name = "ProviderUrl.isExistByProviderIdAndUrl" )
	boolean isExist( @Param( "providerId" ) int providerId, @Param( "url" ) String url );

	@Query( name = "ProviderUrl.findUrlForUpdate" )
	List<ProviderUrl> findUrlForUpdate( @Param( "providerId" ) int providerId );
}
