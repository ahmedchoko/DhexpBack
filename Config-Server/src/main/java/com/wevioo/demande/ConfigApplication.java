package com.wevioo.demande;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

@EnableConfigServer
@SpringBootApplication
@EnableAutoConfiguration(exclude= {org.springframework.boot.autoconfigure.gson.GsonAutoConfiguration.class})
@ComponentScan
@EnableEurekaClient
public class ConfigApplication {

	public static void main(final String[] args) {
		SpringApplication.run(ConfigApplication.class, args);
	}

}
