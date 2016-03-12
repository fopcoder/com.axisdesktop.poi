package com.axisdesktop.poi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.axisdesktop.poi.entity.Location;
import com.axisdesktop.poi.repository.LocationRepository;

@Service
public class LocationServiceImpl implements LocationService {
	@Autowired
	private LocationRepository locRepo;

	@Override
	public List<Location> findAll() {
		return locRepo.findAll();
	}

}
