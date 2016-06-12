package com.axisdesktop.crawler.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.axisdesktop.crawler.entity.ProviderData;

public interface ProviderDataRepository extends BaseRepository<ProviderData, Long> {
	@Query( name = "ProviderData.getIdByUrlIdAndTypeId" )
	Long getIdByUrlIdAndTypeId( @Param( "urlId" ) long urlId, @Param( "typeId" ) int typeId );

	@Modifying
	@Query( value = "DELETE FROM crawler.provider_data WHERE parent_id = :parentId AND type_id = 7", nativeQuery = true )
	void clearCommentsByParentId( @Param( "parentId" ) long parentId );
}
