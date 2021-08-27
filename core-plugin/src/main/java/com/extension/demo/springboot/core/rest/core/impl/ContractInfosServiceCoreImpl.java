package com.extension.demo.springboot.core.rest.core.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.extension.demo.springboot.core.rest.ContractInfosService;
import com.extension.demo.springboot.core.rest.bean.ContractInfo;
import com.extension.demo.springboot.core.rest.bean.template.IContractInfo;
import com.extension.demo.springboot.core.rest.core.logic.template.ExtContractInfoServiceLogic;
import com.extension.demo.springboot.core.rest.param.GetContractInfosParams;
import com.extension.demo.springboot.core.rest.param.bean.ContractInfoListParamBean;

@RestController
public class ContractInfosServiceCoreImpl implements ContractInfosService {
	
	@Autowired(required = false)
	private ExtContractInfoServiceLogic  extcontractInfoServiceLogic;
	
	@GetMapping("/contractinfos")
	@Override
	public <T extends IContractInfo> List<T>  getContractInfos(final GetContractInfosParams beanParam) {

		List<ContractInfo> contractList = new ArrayList<ContractInfo>();
		if (beanParam.getContractNumber() == null && beanParam.getContractNumber().length() > 12) {
			return (List<T>) contractList;
		}
		ContractInfoListParamBean contractInfoListParamBean = getContractInfoBeanFromParam(beanParam);
		if (extcontractInfoServiceLogic != null) {
			return extcontractInfoServiceLogic.getContractInfos(contractInfoListParamBean);
		}

		contractInfoListParamBean.setContractDescription("This contract is created in Core Plugin");
		contractInfoListParamBean.setContractType("Core Type");
		contractList.add(translate(new ContractInfo(), contractInfoListParamBean));
		return (List<T>) contractList;

	}
	
	private ContractInfo translate(ContractInfo contractInfo ,ContractInfoListParamBean contractInfoListParamBean ) {
		contractInfo.setContractNumber(contractInfoListParamBean.getContractNumber());
		contractInfo.setContractType(contractInfoListParamBean.getContractType());
		contractInfo.setContractDescription(contractInfoListParamBean.getContractDescription());
		return contractInfo;
	}
	
	private ContractInfoListParamBean getContractInfoBeanFromParam(GetContractInfosParams beanParam){
		ContractInfoListParamBean contractInfoListParamBean =new ContractInfoListParamBean();
		contractInfoListParamBean.setContractNumber(beanParam.getContractNumber());
		return contractInfoListParamBean;
	}
}
