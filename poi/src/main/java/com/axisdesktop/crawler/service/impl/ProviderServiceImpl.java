package com.axisdesktop.crawler.service.impl;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.axisdesktop.crawler.entity.Provider;
import com.axisdesktop.crawler.entity.ProviderUrl;
import com.axisdesktop.crawler.repository.ProviderRepository;
import com.axisdesktop.crawler.repository.ProviderUrlRepository;
import com.axisdesktop.crawler.service.ProviderService;

@Service
public class ProviderServiceImpl implements ProviderService {
	private ProviderRepository provRepo;
	private ProviderUrlRepository provUrlRepo;

	@Autowired
	public ProviderServiceImpl( ProviderRepository provRepo, ProviderUrlRepository provFeedUriRepo ) {
		this.provRepo = provRepo;
		this.provUrlRepo = provFeedUriRepo;
	}

	@Override
	public List<Provider> findAll() {
		return provRepo.findAll();
	}

	@Override
	public Provider load( int id ) {
		return provRepo.findOne( id );
	}

	@Override
	public List<Provider> findByStatusId( int id ) {
		return provRepo.findByStatusId( id );
	}

	// ProviderUrl

	@Override
	public ProviderUrl loadUrl( long id ) {
		if( id == 0 ) throw new IllegalArgumentException( "id == 0" );

		return this.provUrlRepo.findOne( id );
	}

	@Override
	public ProviderUrl loadUrl( int providerId, String url ) {
		if( providerId == 0 ) throw new IllegalArgumentException( "id == 0" );
		if( url == null ) throw new IllegalArgumentException( "url == null" );

		List<ProviderUrl> l = provUrlRepo.findByProviderIdAndUrl( providerId, url, new PageRequest( 0, 1 ) );

		if( l != null && l.size() > 0 ) {
			return l.get( 0 );
		}

		return null;
	}

	@Override
	public ProviderUrl updateUrl( ProviderUrl pu ) {
		if( pu == null ) throw new IllegalArgumentException( "ProviderUrl is null" );
		if( pu.getId() == 0 ) throw new IllegalArgumentException( "can't update new ProviderUrl" );
		if( this.provUrlRepo.findOne( pu.getId() ) == null )
			throw new IllegalArgumentException( "ProviderUrl " + pu + " not found" );

		return this.provUrlRepo.save( pu );
	}

	@Override
	public ProviderUrl createUrl( ProviderUrl pu ) {
		if( pu == null ) throw new IllegalArgumentException( "ProviderUrl is null" );
		if( pu.getId() != 0 ) throw new IllegalArgumentException( "id must be 0" );

		return this.provUrlRepo.save( pu );
	}

	@Override
	public void deleteUrl( long id ) {
		if( id == 0 ) throw new IllegalArgumentException( "id == 0" );

		this.provUrlRepo.delete( id );
	}

	@Override
	public List<ProviderUrl> findActiveFeedUrl( int providerId ) {
		Calendar cal = Calendar.getInstance();
		cal.add( Calendar.MINUTE, -15 );

		return provUrlRepo.findActiveFeedUrl( providerId, cal, 10 );
	}

	@Override
	public boolean isUrlExist( int providerId, String url ) {
		return provUrlRepo.checkByProviderIdAndUrl( providerId, url );
	}

}
