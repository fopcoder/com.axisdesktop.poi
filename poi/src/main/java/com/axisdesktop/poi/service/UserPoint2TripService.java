package com.axisdesktop.poi.service;

import com.axisdesktop.poi.entity.UserPoint2Trip;

public interface UserPoint2TripService {

	UserPoint2Trip save( UserPoint2Trip upt );

	UserPoint2Trip getLast( long tripId );

}
