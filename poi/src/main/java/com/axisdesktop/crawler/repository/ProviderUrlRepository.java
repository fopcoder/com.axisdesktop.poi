package com.axisdesktop.crawler.repository;

import java.util.Calendar;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.axisdesktop.crawler.entity.ProviderUrl;

public interface ProviderUrlRepository extends JpaRepository<ProviderUrl, Long> {
	@Query( name = "ProviderUrl.findActiveFeedUrl" )
	List<ProviderUrl> findActiveFeedUrl( @Param( "providerId" ) int providerId, @Param( "waitFor" ) Calendar cal,
			@Param( "maxTries" ) int maxTries );

	List<ProviderUrl> findByProviderIdAndUrl( int providerId, String url, Pageable page );

	@Query( name = "ProviderUrl.isExistByProviderIdAndUrl" )
	boolean isExistByProviderIdAndUrl( @Param( "providerId" ) int providerId, @Param( "url" ) String url );

	@Query( name = "ProviderUrl.fidUrlForUpdate" )
	List<ProviderUrl> findUrlForUpdate( @Param( "providerId" ) int providerId );
}
