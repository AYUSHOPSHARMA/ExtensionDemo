package com.extension.demo.springboot.custom.rest.logic.ext.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.extension.demo.springboot.core.rest.bean.ContractInfo;
import com.extension.demo.springboot.core.rest.core.logic.template.ExtContractInfoServiceLogic;
import com.extension.demo.springboot.core.rest.param.bean.ContractInfoListParamBean;
import com.extension.demo.springboot.custom.rest.bean.ext.impl.ContractInfoExt;

@Service
public class ExtContractInfoServiceLogicImpl  implements ExtContractInfoServiceLogic{

	@Override
	public List<ContractInfoExt> getContractInfos(ContractInfoListParamBean contractInfoListParamBean) {
		List<ContractInfoExt> contractList = new ArrayList<ContractInfoExt>();
		ContractInfoExt contractInfoExt=new ContractInfoExt();
		contractInfoListParamBean.setContractDescription("This contract is created in Custom Plugin");
		contractInfoListParamBean.setContractType("Custom Type");
		contractInfoExt.setCustomField1("This is custom field 1 added through extension");
		contractInfoExt.setCustomField2("This is custom field 2 added through extension");
		contractInfoExt.setCustomField3(Long.valueOf("987654321"));
		contractList.add(translate(contractInfoExt, contractInfoListParamBean));
		return contractList;

	}
	
	private ContractInfoExt translate(ContractInfoExt contractInfo ,ContractInfoListParamBean contractInfoListParamBean ) {
		contractInfo.setContractNumber(contractInfoListParamBean.getContractNumber());
		contractInfo.setContractType(contractInfoListParamBean.getContractType());
		contractInfo.setContractDescription(contractInfoListParamBean.getContractDescription());
		return contractInfo;
	}
	

}
