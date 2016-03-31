package com.axisdesktop.poi.service;

import java.util.Arrays;
import java.util.LinkedList;
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

	@Override
	public List<String[]> findPointsInBoundingBox( double south, double west, double north, double east ) {
		List<Object[]> l = locRepo.findPointsInBoundingBox( south, west, north, east );

		List<String[]> res = new LinkedList<>();

		System.out.println( l.size() + " ======================" );
		for( Object[] s : l ) {
			System.out.println( Arrays.toString( s ) );
			res.add( new String[] { s[0].toString(), s[1].toString(), s[2].toString(), (String)s[3] } );
		}

		// System.out.println( locRepo.findPointsInBoundingBox( south, west, north, east ) );
		return res;
	}

}
