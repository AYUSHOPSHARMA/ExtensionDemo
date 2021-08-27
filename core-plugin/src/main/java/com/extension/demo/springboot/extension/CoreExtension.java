package com.extension.demo.springboot.extension;

import java.util.Map;

import org.springframework.context.ConfigurableApplicationContext;

import com.extension.demo.springboot.ActivatorCore;

public class CoreExtension {
	
	public static <T> Map<String, ? extends Object> getAllExtension(T cl){
		 
		 ConfigurableApplicationContext appContext = ActivatorCore.getAppContext();
		 Map<String, ? extends Object> references = appContext.getBeanFactory().getBeansOfType((Class<T>) cl);
		 return references;
	 }

}
