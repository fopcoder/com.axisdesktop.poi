package com.axisdesktop.crawler.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.axisdesktop.crawler.entity.ProviderData;

public interface ProviderDataRepository extends JpaRepository<ProviderData, Long> {
	@Query( name = "ProviderData.getIdByUrlId" )
	Long getIdByUrlId( @Param( "urlId" ) long urlId );

	@Query( name = "ProviderData.clearCommentsByParentId" )
	void clearCommentsByParentId( @Param( "parentId" ) long parentId );
}
