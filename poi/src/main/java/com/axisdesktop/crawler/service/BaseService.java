package com.axisdesktop.crawler.service;

import java.io.Serializable;
import java.util.List;

public interface BaseService<E, ID extends Serializable> {

	void delete( ID id );

	void delete( E entity );

	void delete( List<E> entity );
}
