package com.axisdesktop.crawler.service.impl;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.repository.JpaRepository;

import com.axisdesktop.crawler.service.SimpleDataService;

public abstract class SimpleDataServiceImpl<T> implements SimpleDataService<T> {
	// private JpaRepository<T, Integer> repo;
	//
	// @Override
	// public List<T> list() {
	// return repo.findAll( new Sort( Direction.ASC, "id" ) );
	// }
}
