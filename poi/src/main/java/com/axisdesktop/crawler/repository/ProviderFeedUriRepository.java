package com.axisdesktop.crawler.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.axisdesktop.crawler.entity.ProviderFeedUri;

public interface ProviderFeedUriRepository extends JpaRepository<ProviderFeedUri, Integer> {
	@Query( name = "ProviderFeedUri.getActive" )
	List<ProviderFeedUri> getActive( @Param( "providerId" ) int providerId );
}
