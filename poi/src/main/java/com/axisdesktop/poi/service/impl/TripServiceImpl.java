package com.axisdesktop.poi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	public List<Trip> findTrip( Specification<Trip> spec ) {
		return tripRepo.findAll( spec );
	}

	@Override
	public Trip createTrip( Trip trip ) {
		return tripRepo.save( trip );
	}

	@Override
	public Trip getTrip( long id ) {
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

	@Override
	public List<Trip> findDay( Specification<Trip> spec ) {
		return tripRepo.findAll( spec );
	}

	@Override
	public Trip createDay( Trip day ) {
		return tripRepo.save( day );
	}

	@Override
	public Trip getDay( long id ) {
		return tripRepo.findOne( id );
	}

	@Override
	public Trip updateDay( Trip day ) {
		return tripRepo.save( day );
	}

	@Override
	public void deleteDay( long id ) {
		tripRepo.delete( id );
	}

}
