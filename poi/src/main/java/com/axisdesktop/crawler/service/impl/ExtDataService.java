package com.axisdesktop.crawler.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.axisdesktop.crawler.entity.CrawlerProxy;
import com.axisdesktop.crawler.entity.ProviderData;
import com.axisdesktop.crawler.entity.ProviderDataType;
import com.axisdesktop.crawler.helper.DataSpecification;
import com.axisdesktop.crawler.repository.ProviderDataRepository;
import com.axisdesktop.crawler.repository.ProviderDataTypeRepository;
import com.axisdesktop.crawler.service.ProviderDataService;
import com.axisdesktop.utils.HttpUtils;

import ch.ralscha.extdirectspring.annotation.ExtDirectMethod;
import ch.ralscha.extdirectspring.annotation.ExtDirectMethodType;
import ch.ralscha.extdirectspring.bean.ExtDirectStoreReadRequest;
import ch.ralscha.extdirectspring.bean.ExtDirectStoreResult;

@Service( "dataService" )
public class ExtDataService {
	@Autowired
	private ProviderDataTypeRepository dataTypeRepo;
	@Autowired
	private Environment env;
	@Autowired
	private ProviderDataRepository dataRepo;
	@Autowired
	private ProviderDataService dataService;

	@ExtDirectMethod( ExtDirectMethodType.STORE_READ )
	public ExtDirectStoreResult<ProviderData> list( ExtDirectStoreReadRequest readRequest ) {
		DataSpecification pr = new DataSpecification( readRequest, env );
		Page<ProviderData> pageResult;
		Pageable pageRequest = HttpUtils.createPageable( readRequest );
		pageResult = this.dataRepo.findAll( pr, pageRequest );

		return new ExtDirectStoreResult<>( pageResult.getTotalElements(), pageResult.getContent() );
	}

	@ExtDirectMethod( ExtDirectMethodType.STORE_MODIFY )
	public void delete( List<ProviderData> proxyList ) {
		this.dataService.delete( proxyList );
	}

	@ExtDirectMethod( ExtDirectMethodType.STORE_READ )
	public ExtDirectStoreResult<ProviderDataType> typeList( ExtDirectStoreReadRequest readRequest ) {
		Pageable pageRequest = HttpUtils.createPageable( readRequest );
		Page<ProviderDataType> pageResult = this.dataTypeRepo.findAll( pageRequest );

		return new ExtDirectStoreResult<>( pageResult.getTotalElements(), pageResult.getContent() );
	}

}
