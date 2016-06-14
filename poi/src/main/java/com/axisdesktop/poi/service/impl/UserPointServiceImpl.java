package com.axisdesktop.poi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.axisdesktop.poi.entity.UserPoint;
import com.axisdesktop.poi.repository.UserPointRepository;
import com.axisdesktop.poi.service.UserPointService;

@Service
public class UserPointServiceImpl implements UserPointService {
	private UserPointRepository pointRepo;

	@Autowired
	public UserPointServiceImpl( UserPointRepository pointRepo ) {
		this.pointRepo = pointRepo;
	}

	@Override
	public List<UserPoint> list( Specification<UserPoint> spec ) {
		return pointRepo.findAll( spec );
	}

	@Override
	public UserPoint create( UserPoint point ) {
		return pointRepo.save( point );
	}

	@Override
	public UserPoint createTripPoint( UserPoint point ) {
		// TODO Auto-generated method stub
		return null;
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

}
