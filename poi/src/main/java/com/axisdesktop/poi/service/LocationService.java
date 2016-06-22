package com.axisdesktop.poi.service;

import java.util.List;
import java.util.Map;

import com.axisdesktop.poi.entity.Location;

public interface LocationService {
	List<Location> findAll();

	Location load( long id );

	List<String[]> findPointsInBoundingBox( double south, double west, double north, double east );

	Map<String, String> getPoiData( long id );
}
