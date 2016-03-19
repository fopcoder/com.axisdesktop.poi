package com.axisdesktop.crawler.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.axisdesktop.crawler.entity.Provider;

public interface ProviderRepository extends JpaRepository<Provider, Integer> {
	List<Provider> findByStatusId( @Param( "statusId" ) int id );
}
