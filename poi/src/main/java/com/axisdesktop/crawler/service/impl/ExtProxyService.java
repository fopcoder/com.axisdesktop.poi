package com.axisdesktop.crawler.service.impl;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.axisdesktop.crawler.entity.CrawlerProxy;
import com.axisdesktop.crawler.helper.ProxyBatchValidator;
import com.axisdesktop.crawler.helper.ProxySpecification;
import com.axisdesktop.crawler.repository.ProxyRepository;
import com.axisdesktop.crawler.service.ProxyService;
import com.axisdesktop.utils.HttpUtils;

import ch.ralscha.extdirectspring.annotation.ExtDirectMethod;
import ch.ralscha.extdirectspring.annotation.ExtDirectMethodType;
import ch.ralscha.extdirectspring.bean.ExtDirectFormPostResult;
import ch.ralscha.extdirectspring.bean.ExtDirectStoreReadRequest;
import ch.ralscha.extdirectspring.bean.ExtDirectStoreResult;

@Service( "proxyService" )
public class ExtProxyService {
	private ProxyRepository proxyRepo;
	private ProxyService proxyService;
	private Environment env;

	public ExtProxyService() {
	}

	@Autowired
	public ExtProxyService( ProxyRepository proxyRepo, ProxyService proxyService, Environment env ) {
		this.proxyRepo = proxyRepo;
		this.proxyService = proxyService;
		this.env = env;
	}

	@ExtDirectMethod( ExtDirectMethodType.STORE_READ )
	public ExtDirectStoreResult<CrawlerProxy> list( ExtDirectStoreReadRequest readRequest ) {
		System.err.println( readRequest );

		ProxySpecification pr = new ProxySpecification( readRequest, env );
		Page<CrawlerProxy> pageResult;
		Pageable pageRequest = HttpUtils.createPageable( readRequest );
		pageResult = this.proxyRepo.findAll( pr, pageRequest );

		return new ExtDirectStoreResult<>( pageResult.getTotalElements(), pageResult.getContent() );

	}

	@ExtDirectMethod( ExtDirectMethodType.STORE_MODIFY )
	public void delete( List<CrawlerProxy> proxyList ) {
		this.proxyService.delete( proxyList );
	}

	@ExtDirectMethod( ExtDirectMethodType.FORM_POST )
	public ExtDirectFormPostResult batchCreate( @Valid ProxyBatchValidator info, BindingResult result ) {
		StringBuilder sb = new StringBuilder();

		if( !result.hasErrors() ) {
			String[] rows = info.proxyText.split( "\n" );

			Pattern hostPattern = Pattern.compile( "(\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3})" );
			Pattern portPattern = Pattern.compile( "(\\d{2,5})" );

			for( String s : rows ) {
				Matcher hostMatcher = hostPattern.matcher( s );

				if( hostMatcher.find() ) {
					String host = hostMatcher.group( 0 );

					Matcher portMatcher = portPattern.matcher( s.split( host, 2 )[1] );
					if( portMatcher.find() ) {
						int port = Integer.parseInt( portMatcher.group( 0 ) );

						if( this.proxyRepo.isExistByHostAndPort( host, port ) ) {
							sb.append( String.format( "%s:%d - EXISTS\n", host, port ) );
						}
						else {
							try {
								CrawlerProxy cp = new CrawlerProxy( host, port );
								this.proxyRepo.save( cp );
								sb.append( String.format( "%s:%d - OK\n", host, port ) );
							}
							catch( Exception e ) {
								sb.append( String.format( "%s:%d - ERROR (%s)\n", host, port, e.getMessage() ) );
								e.printStackTrace();
							}
						}

					}
				}
			}
		}

		ExtDirectFormPostResult res = new ExtDirectFormPostResult( result );
		res.addResultProperty( "data", sb.toString() );

		return res;
	}
}
