package com.axisdesktop.poi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.axisdesktop.poi.entity.Location;

public interface LocationRepository extends JpaRepository<Location, Integer> {

}
