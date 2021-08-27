package com.extension.demo.springboot.core.rest;

import java.util.List;

import com.extension.demo.springboot.core.rest.bean.template.IContractInfo;
import com.extension.demo.springboot.core.rest.param.GetContractInfosParams;

public interface ContractInfosService {

	public  <T extends IContractInfo> List<T>  getContractInfos(final GetContractInfosParams beanParam);
}
