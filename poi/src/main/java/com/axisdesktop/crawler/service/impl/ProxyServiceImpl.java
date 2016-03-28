package com.axisdesktop.crawler.service.impl;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.axisdesktop.crawler.entity.CrawlerProxy;
import com.axisdesktop.crawler.entity.CrawlerProxyStatus;
import com.axisdesktop.crawler.repository.ProxyRepository;
import com.axisdesktop.crawler.repository.ProxyStatusRepository;
import com.axisdesktop.crawler.service.ProxyService;
import com.axisdesktop.utils.Utils;

@Service
public class ProxyServiceImpl implements ProxyService {
	@Autowired
	private ProxyRepository proxyRepo;
	@Autowired
	private ProxyStatusRepository proxyStausRepo;

	public ProxyServiceImpl() {
	}

	public ProxyServiceImpl( ProxyRepository proxyRepo, ProxyStatusRepository proxyStausRepo ) {
		this.proxyRepo = proxyRepo;
		this.proxyStausRepo = proxyStausRepo;
	}

	@Override
	public List<CrawlerProxy> findAll() {
		return proxyRepo.findAll();
	}

	@Override
	public CrawlerProxy load( int id ) {
		if( id <= 0 ) throw new IllegalArgumentException( "proxyId must be > 0" );
		return proxyRepo.findOne( id );
	}

	@Override
	public CrawlerProxy update( CrawlerProxy crawlerProxy ) {
		if( crawlerProxy == null ) throw new IllegalArgumentException( "proxy is null" );
		if( crawlerProxy.getId() == 0 ) throw new IllegalArgumentException( "can't update new proxy" );
		if( this.proxyRepo.findOne( crawlerProxy.getId() ) == null )
			throw new IllegalArgumentException( "proxy " + crawlerProxy + " not found" );

		return this.proxyRepo.save( crawlerProxy );
	}

	@Override
	public Proxy getRandomActiveProxy() {
		Proxy proxy = null;
		Calendar cal = Calendar.getInstance();
		cal.add( Calendar.MINUTE, -15 );

		List<CrawlerProxy> proxyList;
		while( !( proxyList = proxyRepo.getRandomActiveProxy( cal, 10, new PageRequest( 0, 1 ) ) ).isEmpty() ) {
			CrawlerProxy crawlerProxy = proxyList.get( 0 );
			proxy = new Proxy( Type.HTTP, new InetSocketAddress( crawlerProxy.getHost(), crawlerProxy.getPort() ) );

			System.out.println( "proxy: start connect " );
			try {
				HttpURLConnection uc = Utils.getConnection( "http://google.com/", proxy );
				System.out.println( "proxy: google recived " );
				if( uc.getResponseCode() != 200 ) {
					throw new IllegalStateException(
							String.format( "%d\n%s", uc.getResponseCode(), uc.getResponseMessage() ) );
				}
				System.out.println( "proxy: external recived " );
				crawlerProxy.setStatusId( 1 );
				crawlerProxy.setLog( null );
				crawlerProxy.setTries( 0 );
				crawlerProxy.setFetched( Calendar.getInstance() );

				this.update( crawlerProxy );
				break;
			}
			catch( IllegalStateException | IOException e ) {
				proxy = null;

				crawlerProxy.setStatusId( 3 );
				crawlerProxy.setLog( e.getMessage() );
				crawlerProxy.setTries( crawlerProxy.getTries() + 1 );
				crawlerProxy.setFetched( Calendar.getInstance() );

				this.update( crawlerProxy );
			}
			System.out.println( "proxy: finish" );
		}

		return proxy;
	}

	@Override
	public CrawlerProxyStatus getProxyStatusById( int id ) {
		return proxyStausRepo.findOne( id );
	}

}
