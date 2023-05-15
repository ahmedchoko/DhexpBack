package com.wevioo.demande;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@EnableDiscoveryClient
@SpringBootApplication
public class Main {

	public static void main(final String[] args) {
		SpringApplication.run(Main.class, args);
	}
}
