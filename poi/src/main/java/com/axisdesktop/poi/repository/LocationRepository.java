package com.axisdesktop.poi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.axisdesktop.poi.entity.Location;
import com.axisdesktop.poi.helper.LocationInfo;

public interface LocationRepository extends JpaRepository<Location, Long> {

	@Query( value = "SELECT l.id, ST_Y(l.point) AS latitude, ST_X(l.point) AS longitude, ltr.name, 0 isUserPoint "
			+ "FROM poi.location l LEFT JOIN poi.location_tr ltr ON ltr.location_id = l.id AND ltr.language_id = 'ru' "
			+ "WHERE l.point&& ST_MakeEnvelope( :west, :south, :east, :north, 4326 ) AND l.id NOT IN ( "
			+ "SELECT point_id FROM poi.user_point "
			+ "WHERE point && ST_MakeEnvelope( :west, :south, :east, :north, 4326 ) "
			+ "AND point_id IS NOT NULL AND user_id = :userId ) " //
			+ "UNION ALL " //
			+ "SELECT id, ST_Y(point) AS latitude, ST_X(point) AS longitude, name, 1 isUserPoint "
			+ "FROM poi.user_point WHERE point && ST_MakeEnvelope( :west, :south, :east, :north, 4326 ) AND user_id = :userId", nativeQuery = true )

	// @Query( value = "SELECT l.id, ST_Y(l.point) AS latitude, ST_X(l.point) AS longitude, ltr.name "
	// + "FROM poi.location l "
	// + "LEFT JOIN poi.location_tr ltr ON ltr.location_id = l.id AND ltr.language_id = 'ru' "
	// + "WHERE l.point && ST_MakeEnvelope(:west, :south, :east, :north, 4326)", nativeQuery = true )
	List<Object[]> findPointsInBoundingBox( @Param( "south" ) double south, @Param( "west" ) double west,
			@Param( "north" ) double north, @Param( "east" ) double east, @Param( "userId" ) long userId );

	@Query( nativeQuery = true, name = "loadLocationInfo" )
	LocationInfo loadLocationInfo( @Param( "id" ) long id );
}
