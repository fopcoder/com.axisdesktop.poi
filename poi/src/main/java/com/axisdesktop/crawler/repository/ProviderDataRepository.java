package com.axisdesktop.crawler.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.axisdesktop.crawler.entity.ProviderData;

public interface ProviderDataRepository extends JpaRepository<ProviderData, Long> {

}
