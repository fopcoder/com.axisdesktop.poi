package com.axisdesktop.crawler.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.axisdesktop.crawler.service.ProxyService;

@Component
public class DorogaCrawler {
	private ProxyService proxyService;

	@Autowired
	public DorogaCrawler( ProxyService proxyService ) {
		this.proxyService = proxyService;
	}

	public DorogaCrawler() {
	}

	public void test() {
		System.out.println( proxyService.findAll() );
	}
}
