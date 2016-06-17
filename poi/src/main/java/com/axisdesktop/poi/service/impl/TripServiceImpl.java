package com.axisdesktop.poi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.axisdesktop.poi.entity.Trip;
import com.axisdesktop.poi.repository.TripRepository;
import com.axisdesktop.poi.service.TripService;

@Service
public class TripServiceImpl implements TripService {
	private TripRepository tripRepo;

	@Autowired
	public TripServiceImpl( TripRepository tripRepo ) {
		this.tripRepo = tripRepo;
	}

	public TripServiceImpl() {
	}

	@Override
	public Page<Trip> findTrip( Specification<Trip> spec, Pageable page ) {
		return tripRepo.findAll( spec, page );
	}

	@Override
	public Trip createTrip( Trip trip ) {
		return tripRepo.save( trip );
	}

	@Override
	public Trip loadTrip( long id ) {
		return tripRepo.findOne( id );
	}

	@Override
	public Trip updateTrip( Trip trip ) {
		return tripRepo.save( trip );
	}

	@Override
	public void deleteTrip( long id ) {
		tripRepo.delete( id );
	}

}
