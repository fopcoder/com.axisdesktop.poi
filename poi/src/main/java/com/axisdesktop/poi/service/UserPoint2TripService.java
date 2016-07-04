package com.axisdesktop.poi.service;

import com.axisdesktop.poi.entity.UserPoint2Trip;

public interface UserPoint2TripService {

	UserPoint2Trip load( long pointId, long tripId );

	UserPoint2Trip save( UserPoint2Trip upt );

	UserPoint2Trip getPrev( UserPoint2Trip pt );

	UserPoint2Trip getNext( UserPoint2Trip pt );

	UserPoint2Trip getLast( long tripId );

	void delete( long id );

}
