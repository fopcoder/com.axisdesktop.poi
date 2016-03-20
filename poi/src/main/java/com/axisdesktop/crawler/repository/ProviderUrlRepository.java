package com.axisdesktop.crawler.repository;

import java.util.Calendar;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.axisdesktop.crawler.entity.ProviderUrl;

public interface ProviderUrlRepository extends JpaRepository<ProviderUrl, Long> {
	@Query( name = "ProviderUrl.findActiveFeedUri" )
	List<ProviderUrl> findActiveFeedUri( @Param( "providerId" ) int providerId, @Param( "waitFor" ) Calendar cal,
			@Param( "maxTries" ) int maxTries );
}
