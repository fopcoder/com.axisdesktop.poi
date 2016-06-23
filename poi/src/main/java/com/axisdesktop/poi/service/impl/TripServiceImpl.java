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

	@Override
	public Page<Trip> list( Specification<Trip> spec, Pageable page ) {
		return tripRepo.findAll( spec, page );
	}

	@Override
	public Trip load( long id, long userId ) {
		return tripRepo.findOneByIdAndUserId( id, userId );
	}

	@Override
	public Trip load( long id ) {
		return tripRepo.findOne( id );
	}

	@Override
	public Trip create( Trip trip ) {
		return tripRepo.save( trip );
	}

	@Override
	public Trip update( Trip trip ) {
		return tripRepo.save( trip );
	}

	@Override
	public void delete( long id ) {
		tripRepo.delete( id );
	}

}
