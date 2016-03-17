package com.axisdesktop.crawler.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.axisdesktop.crawler.entity.Proxy;

public interface ProxyRepository extends JpaRepository<Proxy, Integer> {

}
