package com.axisdesktop.crawler.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.axisdesktop.base.entity.SimpleEntity;

@NoRepositoryBean
public interface SimpleDataRepository<T extends SimpleEntity> extends JpaRepository<T, Integer> {
	// @Query("select u from #{#entityName} as u where u.email = ?1 ")
	// T findByEmail(String email);
}
