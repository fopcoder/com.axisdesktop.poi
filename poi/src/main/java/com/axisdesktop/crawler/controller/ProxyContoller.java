package com.axisdesktop.crawler.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.axisdesktop.crawler.entity.CrawlerProxy;
import com.axisdesktop.crawler.service.ProxyService;

@RestController
@RequestMapping( "/_admin/proxy" )
public class ProxyContoller {
	@Autowired
	private ProxyService proxyServ;

	@RequestMapping( name = "/", method = RequestMethod.GET )
	public List<CrawlerProxy> proxyList( String rrr ) {
		System.err.println( rrr );
		return proxyServ.findAll();
	}

	// @RequestMapping( name = "{id}", method = RequestMethod.GET )
	// public CrawlerProxy proxyItem( @PathVariable int id ) {
	// return null;
	// }
}
