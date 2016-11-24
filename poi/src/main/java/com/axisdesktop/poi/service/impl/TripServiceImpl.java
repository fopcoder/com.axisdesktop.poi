package com.axisdesktop.poi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
		List<Trip> trips = tripRepo.findByParentId( id );

		for( Trip t : trips ) {
			tripRepo.delete( t );
		}

		tripRepo.delete( id );
	}

	@Override
	public Trip append( Trip trip ) {
		System.err.println( trip );
		Trip last = tripRepo.findTop1ByParentIdAndUserIdOrderByTorderDesc( trip.getParentId(), trip.getUserId() );

		if( last != null ) {
			trip.setTorder( last.getTorder() + 10 );
		}

		return tripRepo.save( trip );
	}

	@Override
	public Trip getPrev( Trip trip ) {
		return tripRepo.findTop1ByParentIdAndTorderLessThanOrderByTorderDesc( trip.getParentId(), trip.getTorder() );
	}

	@Override
	public Trip getNext( Trip trip ) {
		return tripRepo.findTop1ByParentIdAndTorderGreaterThanOrderByTorderAsc( trip.getParentId(), trip.getTorder() );
	}

	@Override
	@Transactional
	public Trip moveUp( Trip trip ) {
		Trip tPrev = this.getPrev( trip );

		if( tPrev != null && trip != null ) {
			int tmp = tPrev.getTorder();
			tPrev.setTorder( trip.getTorder() );
			trip.setTorder( tmp );

			tripRepo.save( tPrev );
			tripRepo.save( trip );
		}

		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public Trip moveDown( Trip trip ) {
		Trip tNext = this.getNext( trip );

		if( tNext != null && trip != null ) {
			int tmp = tNext.getTorder();
			tNext.setTorder( trip.getTorder() );
			trip.setTorder( tmp );

			tripRepo.save( tNext );
			tripRepo.save( trip );
		}
		return null;
	}

	@Override
	public List<Trip> children( Trip trip ) {
		return tripRepo.findByParentId( trip.getId() );
	}

}
