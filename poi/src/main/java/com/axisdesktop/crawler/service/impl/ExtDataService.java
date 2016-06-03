package com.axisdesktop.crawler.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.axisdesktop.crawler.entity.ProviderDataType;
import com.axisdesktop.crawler.repository.ProviderDataTypeRepository;
import com.axisdesktop.utils.HttpUtils;

import ch.ralscha.extdirectspring.annotation.ExtDirectMethod;
import ch.ralscha.extdirectspring.annotation.ExtDirectMethodType;
import ch.ralscha.extdirectspring.bean.ExtDirectStoreReadRequest;
import ch.ralscha.extdirectspring.bean.ExtDirectStoreResult;

@Service( "dataService" )
public class ExtDataService {
	@Autowired
	private ProviderDataTypeRepository dataTypeRepo;

	@ExtDirectMethod( ExtDirectMethodType.STORE_READ )
	public ExtDirectStoreResult<ProviderDataType> typeList( ExtDirectStoreReadRequest readRequest ) {
		Pageable pageRequest = HttpUtils.createPageable( readRequest );
		Page<ProviderDataType> pageResult = this.dataTypeRepo.findAll( pageRequest );

		return new ExtDirectStoreResult<>( pageResult.getTotalElements(), pageResult.getContent() );
	}
}
