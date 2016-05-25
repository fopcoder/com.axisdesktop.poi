package com.axisdesktop.crawler.service.impl;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import com.axisdesktop.crawler.entity.CrawlerProxy;
import com.axisdesktop.crawler.helper.ProxyBatchValidator;
import com.axisdesktop.crawler.repository.ProxyRepository;
import com.axisdesktop.utils.HttpUtils;

import ch.ralscha.extdirectspring.annotation.ExtDirectMethod;
import ch.ralscha.extdirectspring.annotation.ExtDirectMethodType;
import ch.ralscha.extdirectspring.bean.ExtDirectFormPostResult;
import ch.ralscha.extdirectspring.bean.ExtDirectStoreReadRequest;
import ch.ralscha.extdirectspring.bean.ExtDirectStoreResult;

@Service( "Crawler.proxyService" )
public class ExtProxyService {
	@Autowired
	private ProxyRepository proxyRepo;

	@ExtDirectMethod( ExtDirectMethodType.STORE_READ )
	public ExtDirectStoreResult<CrawlerProxy> list( ExtDirectStoreReadRequest readRequest ) {
		System.err.println( readRequest );

		ProxySpecification pr = new ProxySpecification( readRequest );
		System.err.println( "3333333" );
		Page<CrawlerProxy> pageResult;
		Pageable pageRequest = HttpUtils.createPageable( readRequest );
		pageResult = this.proxyRepo.findAll( pr, pageRequest );

		return new ExtDirectStoreResult<>( pageResult.getTotalElements(), pageResult.getContent() );

	}

	@Transactional
	@ExtDirectMethod( ExtDirectMethodType.STORE_MODIFY )
	public ExtDirectStoreResult<CrawlerProxy> delete( List<CrawlerProxy> proxyList ) {

		for( CrawlerProxy proxy : proxyList ) {
			this.proxyRepo.delete( proxy );
		}

		return new ExtDirectStoreResult<>();
	}

	@ExtDirectMethod( ExtDirectMethodType.FORM_POST )
	public ExtDirectFormPostResult batchCreate( @Valid ProxyBatchValidator info, BindingResult result ) {
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

						CrawlerProxy cp = new CrawlerProxy( host, port );
						this.proxyRepo.save( cp );
					}
				}
			}
		}

		return new ExtDirectFormPostResult( result );
	}
}
