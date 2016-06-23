package com.axisdesktop.poi.repository;

import java.util.List;

import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQuery;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.axisdesktop.poi.entity.Location;
import com.axisdesktop.poi.helper.LocationInfo;

public interface LocationRepository extends JpaRepository<Location, Long> {
	@Query( value = "SELECT l.id, ST_Y(l.point) AS latitude, ST_X(l.point) AS longitude, ltr.name "
			+ "FROM poi.location l "
			+ "LEFT JOIN poi.location_tr ltr ON ltr.location_id = l.id AND ltr.language_id = 'ru' "
			+ "WHERE l.point && ST_MakeEnvelope(:west, :south, :east, :north, 4326)", nativeQuery = true )
	List<Object[]> findPointsInBoundingBox( @Param( "south" ) double south, @Param( "west" ) double west,
			@Param( "north" ) double north, @Param( "east" ) double east );

	@Query( nativeQuery = true, name = "findFuck" )
	// @NamedQuery()
	// LocationInfo loadLocationInfo( @Param("id") long id, @Param("lang") String lang );
	LocationInfo loadLocationInfo( @Param( "id" ) long id );
}
