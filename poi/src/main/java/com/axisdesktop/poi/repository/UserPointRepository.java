package com.axisdesktop.poi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.axisdesktop.poi.entity.UserPoint;

public interface UserPointRepository extends JpaRepository<UserPoint, Long>, JpaSpecificationExecutor<UserPoint> {

}
