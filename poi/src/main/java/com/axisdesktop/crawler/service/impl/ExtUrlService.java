package com.axisdesktop.crawler.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.axisdesktop.crawler.entity.ProviderUrl;
import com.axisdesktop.crawler.entity.ProviderUrlStatus;
import com.axisdesktop.crawler.helper.UrlSpecification;
import com.axisdesktop.crawler.repository.ProviderUrlRepository;
import com.axisdesktop.crawler.repository.ProviderUrlStatusRepository;
import com.axisdesktop.utils.HttpUtils;

import ch.ralscha.extdirectspring.annotation.ExtDirectMethod;
import ch.ralscha.extdirectspring.annotation.ExtDirectMethodType;
import ch.ralscha.extdirectspring.bean.ExtDirectStoreReadRequest;
import ch.ralscha.extdirectspring.bean.ExtDirectStoreResult;

@Service( "urlService" )
public class ExtUrlService {

	@Autowired
	private ProviderUrlStatusRepository statusRepo;
	@Autowired
	private ProviderUrlRepository urlRepo;
	@Autowired
	private Environment env;

	@ExtDirectMethod( ExtDirectMethodType.STORE_READ )
	public ExtDirectStoreResult<ProviderUrl> list( ExtDirectStoreReadRequest readRequest ) {
		UrlSpecification pr = new UrlSpecification( readRequest, env );
		Page<ProviderUrl> pageResult;
		Pageable pageRequest = HttpUtils.createPageable( readRequest );
		pageResult = this.urlRepo.findAll( pr, pageRequest );

		return new ExtDirectStoreResult<>( pageResult.getTotalElements(), pageResult.getContent() );
	}

	@ExtDirectMethod( ExtDirectMethodType.STORE_MODIFY )
	public void delete( List<ProviderUrl> proxyList ) {
		this.urlRepo.delete( proxyList );
	}

	@ExtDirectMethod( ExtDirectMethodType.STORE_READ )
	public ExtDirectStoreResult<ProviderUrlStatus> statusList( ExtDirectStoreReadRequest readRequest ) {
		Pageable pageRequest = HttpUtils.createPageable( readRequest );
		Page<ProviderUrlStatus> pageResult = this.statusRepo.findAll( pageRequest );

		return new ExtDirectStoreResult<>( pageResult.getTotalElements(), pageResult.getContent() );
	}
}
