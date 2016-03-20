package com.axisdesktop.crawler.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.axisdesktop.crawler.entity.ProviderUri;

public interface ProviderUriRepository extends JpaRepository<ProviderUri, Integer> {
	@Query( name = "ProviderUri.findActiveFeedUri" )
	List<ProviderUri> getActiveFeedUri( @Param( "providerId" ) int providerId );
}
