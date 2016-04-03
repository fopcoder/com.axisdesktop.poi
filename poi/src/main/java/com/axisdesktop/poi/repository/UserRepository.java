package com.axisdesktop.poi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.axisdesktop.poi.entity.User;
import com.axisdesktop.poi.entity.UserRole;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByEmail( String email );

	User findByName( String name );

	@Query( name = "User.findRole" )
	List<UserRole> findRole();
}
