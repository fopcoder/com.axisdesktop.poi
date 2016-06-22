package com.axisdesktop.poi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.axisdesktop.poi.entity.UserPoint2Trip;
import com.axisdesktop.poi.repository.UserPoint2TripRepository;
import com.axisdesktop.poi.service.UserPoint2TripService;

@Service
public class UserPoint2TripServiceImpl implements UserPoint2TripService {
	private UserPoint2TripRepository uptRepo;

	@Autowired
	public UserPoint2TripServiceImpl( UserPoint2TripRepository uptRepo ) {
		this.uptRepo = uptRepo;
	}

	@Override
	public UserPoint2Trip save( UserPoint2Trip upt ) {
		return uptRepo.save( upt );
	}

	@Override
	public UserPoint2Trip getLast( long tripId ) {
		return uptRepo.findTop1ByTripIdOrderByPorderDesc( tripId );
	}

}
