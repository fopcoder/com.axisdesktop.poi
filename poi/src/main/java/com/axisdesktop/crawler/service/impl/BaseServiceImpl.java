package com.axisdesktop.crawler.service.impl;

import java.io.Serializable;
import java.util.List;

import com.axisdesktop.crawler.repository.BaseRepository;
import com.axisdesktop.crawler.service.BaseService;

public abstract class BaseServiceImpl<E, ID extends Serializable> implements BaseService<E, ID> {
	@Override
	public void delete( ID id ) {
		this.getRepository().delete( id );
	}

	@Override
	public void delete( E entity ) {
		this.getRepository().delete( entity );
	}

	@Override
	public void delete( List<E> entity ) {
		this.getRepository().delete( entity );
	}

	public abstract BaseRepository<E, ID> getRepository();
}
