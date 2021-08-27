package com.extension.demo.springboot.core.rest.bean;

import com.extension.demo.springboot.core.rest.bean.template.IContractInfo;

public class ContractInfo implements IContractInfo{

   String contractNumber;
	
	String contractType;
	
	String contractDescription;

	public String getContractType() {
		return contractType;
	}

	public void setContractType(String contractType) {
		this.contractType = contractType;
	}

	public String getContractDescription() {
		return contractDescription;
	}

	public void setContractDescription(String contractDescription) {
		this.contractDescription = contractDescription;
	}

	public String getContractNumber() {
		return contractNumber;
	}

	public void setContractNumber(String contractNumber) {
		this.contractNumber = contractNumber;
	}
	
}
