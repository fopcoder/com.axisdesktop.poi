package com.axisdesktop.poi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.axisdesktop.poi.entity.Trip;
import com.axisdesktop.poi.entity.UserPoint;
import com.axisdesktop.poi.entity.UserPoint2Trip;
import com.axisdesktop.poi.repository.UserPointRepository;
import com.axisdesktop.poi.service.UserPoint2TripService;
import com.axisdesktop.poi.service.UserPointService;

@Service
public class UserPointServiceImpl implements UserPointService {
	private UserPointRepository pointRepo;
	private UserPoint2TripService uptService;

	@Autowired
	public UserPointServiceImpl( UserPointRepository pointRepo, UserPoint2TripService uptService ) {
		this.pointRepo = pointRepo;
		this.uptService = uptService;
	}

	@Override
	public Page<UserPoint> list( Specification<UserPoint> spec, Pageable page ) {
		return pointRepo.findAll( spec, page );
	}

	@Override
	public UserPoint load( long id ) {
		return pointRepo.findOne( id );
	}

	@Override
	public UserPoint load( long userId, long pointId ) {
		return pointRepo.findTop1ByUserIdAndPointId( userId, pointId );
	}

	@Override
	public UserPoint create( UserPoint point ) {
		return pointRepo.save( point );
	}

	@Override
	public UserPoint add( UserPoint point, Trip trip ) {
		if( point.getPointId() != null ) {
			UserPoint up = this.load( point.getUserId(), point.getPointId() );
			if( up != null ) {
				point = up;
			}
			else {
				this.create( point );
			}
		}
		else {
			this.create( point );
		}

		if( trip != null ) {
			UserPoint2Trip order = uptService.getLast( trip.getId() );

			UserPoint2Trip up2t = new UserPoint2Trip();
			up2t.setPorder( order == null ? 0 : order.getPorder() + 10 );
			up2t.setTrip( trip );
			up2t.setPoint( point );

			uptService.save( up2t );
		}

		return point;
	}

	@Override
	public UserPoint update( UserPoint point ) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete( long id ) {
		// TODO Auto-generated method stub

	}

	@Override
	public void remove( long pointId, long tripId ) {
		if( pointId > 0 && tripId > 0 ) {
			UserPoint2Trip upt = uptService.load( pointId, tripId );
			if( upt != null ) {
				uptService.delete( upt.getId() );
			}
		}
	}

	@Override
	public Page<UserPoint> listTripPoints( long userId, long tripId ) {
		return pointRepo.findTripPoints( userId, tripId, new PageRequest( 0, 100000 ) );
	}

}
