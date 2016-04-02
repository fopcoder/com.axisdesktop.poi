package com.axisdesktop.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUserEntity, Long> {
	public AppUserEntity findByEmail( String email );
}
