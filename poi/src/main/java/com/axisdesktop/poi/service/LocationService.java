package com.axisdesktop.poi.service;

import java.util.List;

import com.axisdesktop.poi.entity.Location;

public interface LocationService {
	List<Location> findAll();

	List<String[]> findPointsInBoundingBox( double south, double west, double north, double east );
}
