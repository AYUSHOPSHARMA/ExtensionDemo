package com.extension.demo.springboot;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.extension.demo.springboot.controller.ControllerConfig;

@Configuration
@ComponentScan("com.finalhints.osgi")
@Import(ControllerConfig.class)
public class CoreConfig {

}
