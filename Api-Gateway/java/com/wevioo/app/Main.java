package com.wevioo.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

import com.wevioo.parametrage.entities.Fond;

@EnableConfigServer
@SpringBootApplication
@EnableAutoConfiguration(exclude= {org.springframework.boot.autoconfigure.gson.GsonAutoConfiguration.class})
@ComponentScan
@EnableEurekaClient
public class Main {

	public static void main(final String[] args) {
		SpringApplication.run(Main.class, args);
	}
	public Fond fond ;
}
