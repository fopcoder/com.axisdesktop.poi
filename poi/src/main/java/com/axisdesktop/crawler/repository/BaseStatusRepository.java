package com.axisdesktop.crawler.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.axisdesktop.crawler.entity.AbstractStatus;

@NoRepositoryBean
public interface BaseStatusRepository<T extends AbstractStatus> extends JpaRepository<T, Integer> {
	// @Query("select u from #{#entityName} as u where u.email = ?1 ")
	// T findByEmail(String email);
}
