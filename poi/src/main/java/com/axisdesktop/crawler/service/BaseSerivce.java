package com.axisdesktop.crawler.service;

import java.util.List;

public interface BaseSerivce<E> {

	void delete( int id );

	void delete( E entity );

	void delete( List<E> entity );
}
