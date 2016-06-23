package com.axisdesktop.poi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.axisdesktop.poi.entity.Trip;

public interface TripRepository extends JpaRepository<Trip, Long>, JpaSpecificationExecutor<Trip> {
	Trip findOneByIdAndUserId( long id, long userId );
}
