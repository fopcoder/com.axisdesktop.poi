package com.axisdesktop.user;

import org.springframework.data.jpa.repository.JpaRepository;

import com.axisdesktop.poi.entity.AppUserEntity;

public interface AppUserRepository extends JpaRepository<AppUserEntity, Long> {
	public AppUserEntity findByEmail( String email );
}
