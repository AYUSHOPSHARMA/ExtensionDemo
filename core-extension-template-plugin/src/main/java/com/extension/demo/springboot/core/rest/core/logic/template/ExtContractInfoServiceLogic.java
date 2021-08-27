package com.extension.demo.springboot.core.rest.core.logic.template;

import java.util.List;

import com.extension.demo.springboot.core.rest.bean.template.IContractInfo;
import com.extension.demo.springboot.core.rest.param.bean.ContractInfoListParamBean;

public interface ExtContractInfoServiceLogic {
	
	<T extends IContractInfo> List<T>  getContractInfos(ContractInfoListParamBean contractInfoListParamBean);

}
