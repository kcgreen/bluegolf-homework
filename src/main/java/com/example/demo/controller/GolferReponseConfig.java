package com.example.demo.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.RequestScope;

@Configuration
public class GolferReponseConfig {
	
	@Bean
	@RequestScope
	public GolferResponse golferRespnoseGenerator() {
		return new GolferResponse();
	}

}
