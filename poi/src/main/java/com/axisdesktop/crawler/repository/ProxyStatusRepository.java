package com.axisdesktop.crawler.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.axisdesktop.crawler.entity.ProxyStatus;

public interface ProxyStatusRepository extends JpaRepository<ProxyStatus, Integer> {

}
