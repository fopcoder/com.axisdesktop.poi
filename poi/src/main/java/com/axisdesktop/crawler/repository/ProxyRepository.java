package com.axisdesktop.crawler.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.axisdesktop.crawler.entity.Proxy;

public interface ProxyRepository extends JpaRepository<Proxy, Integer> {
	// @Query( "SELECT p FROM Proxy ORDER BY RANDOM() LIMIT 1" )
	// Proxy findOneRandom();
	Proxy selectOneOrderByRandom();
}
