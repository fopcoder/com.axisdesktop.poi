package com.axisdesktop.poi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.axisdesktop.poi.entity.Trip;

public interface TripRepository extends JpaRepository<Trip, Long>, JpaSpecificationExecutor<Trip> {
	Trip findOneByIdAndUserId( long id, long userId );

	Trip findTop1ByParentIdAndUserIdOrderByTorderDesc( Long parentId, long userId );

	Trip findTop1ByParentIdAndTorderLessThanOrderByTorderDesc( Long parentId, int torder );

	Trip findTop1ByParentIdAndTorderGreaterThanOrderByTorderAsc( Long parentId, int torder );

	List<Trip> findByParentId( Long parentId );
}
