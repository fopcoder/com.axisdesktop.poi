package com.axisdesktop.poi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.axisdesktop.poi.entity.UserPoint;

public interface UserPointRepository extends JpaRepository<UserPoint, Long>, JpaSpecificationExecutor<UserPoint> {
	UserPoint findTop1ByUserIdAndPointId( long userId, long pointId );

	@Query( "SELECT p FROM UserPoint p INNER JOIN p.point2trip pt WITH pt.tripId = :tripId  "
			+ "WHERE p.userId = :userId ORDER BY pt.porder" )
	Page<UserPoint> findTripPoints( @Param( "userId" ) long userId, @Param( "tripId" ) long tripId, Pageable page );
}
