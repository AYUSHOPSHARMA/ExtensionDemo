package com.extension.demo.springboot.core.ext.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ExtensionApplication  {

	/**
	 * 
	 */
	static ConfigurableApplicationContext appContext;

	 public static void setAppContext(ConfigurableApplicationContext appContext) {
		ExtensionApplication.appContext = appContext;
	}

	public static ConfigurableApplicationContext getAppContext() {
		return appContext;
	}

	public static void main(String[] args) {
		appContext = SpringApplication.run(ExtensionApplication.class, args);
	}


}
