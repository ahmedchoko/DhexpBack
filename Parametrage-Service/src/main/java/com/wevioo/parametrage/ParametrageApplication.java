package com.wevioo.parametrage;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
@EnableDiscoveryClient
@SpringBootApplication

public class ParametrageApplication {

	public static void main(String[] args) {
		SpringApplication.run(ParametrageApplication.class, args);
	}
	 @Bean
	public ModelMapper modelMapper() {
	   return new ModelMapper();
	}
	
}
