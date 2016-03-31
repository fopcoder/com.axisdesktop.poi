package com.axisdesktop.poi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.axisdesktop.poi.entity.Location;

public interface LocationRepository extends JpaRepository<Location, Integer> {
	@Query( value = "SELECT l.id, ST_Y(l.point) AS latitude, ST_X(l.point) AS longitude, 'point '||l.id AS name FROM poi.location l WHERE l.point && ST_MakeEnvelope(:west, :south, :east, :north, 4326)", nativeQuery = true )
	List<Object[]> findPointsInBoundingBox( @Param( "south" ) double south, @Param( "west" ) double west,
			@Param( "north" ) double north, @Param( "east" ) double east );
}
