package com.axisdesktop.crawler.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import com.axisdesktop.base.entity.SimpleEntity;

@SuppressWarnings( "rawtypes" )
@NoRepositoryBean
public interface SimpleDataRepository<T extends SimpleEntity>
		extends JpaRepository<T, Integer>, JpaSpecificationExecutor<T> {
}
