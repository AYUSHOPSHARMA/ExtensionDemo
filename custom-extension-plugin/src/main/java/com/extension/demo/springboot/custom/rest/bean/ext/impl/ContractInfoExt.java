package com.extension.demo.springboot.custom.rest.bean.ext.impl;

import com.extension.demo.springboot.core.rest.bean.ContractInfo;

public class ContractInfoExt extends ContractInfo{
	

    String customField1;
	
	String customField2;
	
	Long customField3;

	public String getCustomField1() {
		return customField1;
	}

	public void setCustomField1(String customField1) {
		this.customField1 = customField1;
	}

	public String getCustomField2() {
		return customField2;
	}

	public void setCustomField2(String customField2) {
		this.customField2 = customField2;
	}

	public Long getCustomField3() {
		return customField3;
	}

	public void setCustomField3(Long customField3) {
		this.customField3 = customField3;
	}
}
