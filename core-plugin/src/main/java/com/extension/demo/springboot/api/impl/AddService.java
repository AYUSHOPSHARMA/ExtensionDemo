package com.extension.demo.springboot.api.impl;

import org.springframework.stereotype.Service;

import com.extension.demo.springboot.api.CalculatorService;
@Service
public class AddService implements CalculatorService {

	@Override
	public int execute(int n1, int n2) {
		return n1 + n2;
	}

	@Override
	public String name() {
		return "Addition";
	}

}
