package com.axisdesktop.crawler.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.axisdesktop.crawler.service.BaseSerivce;

import ch.ralscha.extdirectspring.annotation.ExtDirectMethod;
import ch.ralscha.extdirectspring.annotation.ExtDirectMethodType;

public class ExtBaseServiceImpl<E> implements BaseSerivce<E> {
	@Autowired
	private BaseSerivce<E> service;

	@Override
	@ExtDirectMethod( ExtDirectMethodType.STORE_MODIFY )
	public void delete( List<E> list ) {
		this.service.delete( list );
	}

	@Override
	@ExtDirectMethod( ExtDirectMethodType.STORE_MODIFY )
	public void delete( int id ) {
		this.service.delete( id );
	}

	@Override
	@ExtDirectMethod( ExtDirectMethodType.STORE_MODIFY )
	public void delete( E entity ) {
		this.service.delete( entity );
	}
}
