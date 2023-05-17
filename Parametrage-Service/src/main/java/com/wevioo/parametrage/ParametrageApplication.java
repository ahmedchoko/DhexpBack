package com.wevioo.parametrage;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableDiscoveryClient
@SpringBootApplication
@EnableJpaRepositories(basePackages = {"com.wevioo.demande.*"})
@ComponentScan(basePackages = {"com.wevioo.demande.*"})
@EntityScan(basePackages = {"com.wevioo.demande.*"})
public class ParametrageApplication {
	public static void main(String[] args) {
		SpringApplication.run(ParametrageApplication.class, args);
	}
	 @Bean
	public ModelMapper modelMapper() {
	   return new ModelMapper();
	}

}
