package com.axisdesktop.poi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.axisdesktop.poi.entity.UserPoint2Trip;

public interface UserPoint2TripRepository
		extends JpaRepository<UserPoint2Trip, Long>, JpaSpecificationExecutor<UserPoint2Trip> {

	UserPoint2Trip findTop1ByTripIdOrderByPorderDesc( long tripId );
}