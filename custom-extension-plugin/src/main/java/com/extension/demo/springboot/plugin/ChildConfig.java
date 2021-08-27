package com.extension.demo.springboot.plugin;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.finalhints.*")
@EnableAutoConfiguration()
public class ChildConfig {

}
