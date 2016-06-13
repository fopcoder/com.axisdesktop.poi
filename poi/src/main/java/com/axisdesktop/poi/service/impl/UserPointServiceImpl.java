package com.axisdesktop.poi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
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
	public UserPoint create( UserPoint point ) {
		return pointRepo.save( point );
	}

}
