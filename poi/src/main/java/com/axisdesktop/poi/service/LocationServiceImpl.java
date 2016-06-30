package com.axisdesktop.poi.service;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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
	public List<String[]> findPointsInBoundingBox( double south, double west, double north, double east, long userId ) {
		List<Object[]> l = locRepo.findPointsInBoundingBox( south, west, north, east, userId );

		List<String[]> res = new LinkedList<>();

		System.out.println( l.size() + " ======================" );
		for( Object[] s : l ) {
			// System.out.println( Arrays.toString( s ) );
			res.add( new String[] { s[0].toString(), s[1].toString(), s[2].toString(), (String)s[3],
					s[4].toString() } );
		}

		// System.out.println( locRepo.findPointsInBoundingBox( south, west, north, east ) );
		return res;
	}

	@Override
	public Map<String, String> getPoiData( long id ) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Location load( long id ) {
		return locRepo.findOne( id );
	}

}
