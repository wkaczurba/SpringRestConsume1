package com.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {
	@Autowired
	RestTemplate restTemplate;
	
	@Bean
	RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
