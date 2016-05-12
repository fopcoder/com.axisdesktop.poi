package com.axisdesktop.crawler.service.impl;

import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.net.URL;
import java.util.Calendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.axisdesktop.crawler.entity.CrawlerProxy;
import com.axisdesktop.crawler.entity.CrawlerProxyStatus;
import com.axisdesktop.crawler.repository.ProxyRepository;
import com.axisdesktop.crawler.repository.ProxyStatusRepository;
import com.axisdesktop.crawler.service.ProxyService;

@Service
public class ProxyServiceImpl implements ProxyService {
	private static final Logger logger = LoggerFactory.getLogger( ProxyServiceImpl.class );

	private ProxyRepository proxyRepo;
	private ProxyStatusRepository proxyStausRepo;

	public ProxyServiceImpl() {
	}

	@Autowired
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
		return getRandomActiveProxy( -15, 10, "http://google.com/" );
	}

	@Override
	public Proxy getRandomActiveProxy( int proxyTimeout, int proxyMaxTries, String proxyTestDomain ) {
		Proxy proxy = null;

		Calendar cal = Calendar.getInstance();
		cal.add( Calendar.MINUTE, proxyTimeout );
		List<CrawlerProxy> proxyList;

		while( !( proxyList = proxyRepo.getRandomActiveProxy( cal, proxyMaxTries, new PageRequest( 0, 1 ) ) )
				.isEmpty() ) {
			CrawlerProxy crawlerProxy = proxyList.get( 0 );
			proxy = new Proxy( Type.HTTP, new InetSocketAddress( crawlerProxy.getHost(), crawlerProxy.getPort() ) );

			try {
				URL url = new URL( proxyTestDomain );
				HttpURLConnection uc = (HttpURLConnection)url.openConnection( proxy );
				uc.setConnectTimeout( 10_000 );
				uc.setRequestMethod( "HEAD" );

				logger.debug( "proxy: test url {} start {}", proxyTestDomain, crawlerProxy.getHost() );

				if( uc.getResponseCode() != 200 ) {
					throw new IllegalStateException(
							String.format( "%d\n%s", uc.getResponseCode(), uc.getResponseMessage() ) );
				}

				logger.debug( "proxy: test url recived " );

				crawlerProxy.setStatusId( 1 );
				crawlerProxy.setLog( null );
				crawlerProxy.setTries( 0 );
				crawlerProxy.setFetched( Calendar.getInstance() );

				this.update( crawlerProxy );
				break;
			}
			catch( Exception e ) {
				logger.debug( "proxy: test url exception {}", e.getMessage() );

				proxy = null;

				crawlerProxy.setStatusId( 3 );
				crawlerProxy.setLog( e.getMessage() );
				crawlerProxy.setTries( crawlerProxy.getTries() + 1 );
				crawlerProxy.setFetched( Calendar.getInstance() );

				this.update( crawlerProxy );
			}
		}

		return proxy;
	}

	@Override
	public CrawlerProxyStatus getProxyStatusById( int id ) {
		return proxyStausRepo.findOne( id );
	}

	@Override
	public CrawlerProxy create( CrawlerProxy proxy ) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CrawlerProxy delete( int id ) {
		// TODO Auto-generated method stub
		return null;
	}

}
