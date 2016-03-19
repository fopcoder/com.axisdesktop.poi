package com.axisdesktop.crawler.repository;

import java.util.Calendar;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.axisdesktop.crawler.entity.Proxy;

public interface ProxyRepository extends JpaRepository<Proxy, Integer> {
	@Query( name = "Proxy.findActiveOrderByRandom" )
	List<Proxy> getRandomActiveProxy( @Param( "waitFor" ) Calendar date, Pageable pageable );
}
