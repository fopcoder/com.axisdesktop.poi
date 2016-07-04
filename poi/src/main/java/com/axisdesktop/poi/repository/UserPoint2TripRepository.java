package com.axisdesktop.poi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.axisdesktop.poi.entity.UserPoint2Trip;

public interface UserPoint2TripRepository
		extends JpaRepository<UserPoint2Trip, Long>, JpaSpecificationExecutor<UserPoint2Trip> {

	UserPoint2Trip findTop1ByTripIdOrderByPorderDesc( long tripId );

	UserPoint2Trip findOneByPointIdAndTripId( long pointId, long tripId );

	UserPoint2Trip findTop1ByTripIdAndPorderLessThanOrderByPorderDesc( long tripId, int porder );

	UserPoint2Trip findTop1ByTripIdAndPorderGreaterThanOrderByPorderAsc( long tripId, int porder );

}
