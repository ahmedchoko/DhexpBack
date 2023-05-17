package com.wevioo.demande;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@EnableDiscoveryClient
@SpringBootApplication
@EnableJpaRepositories(basePackages = {"com.wevioo.parametrage.*","com.wevioo.demande.*"})
@ComponentScan(basePackages = {"com.wevioo.parametrage.*","com.wevioo.demande.*"})
@EntityScan(basePackages = {"com.wevioo.parametrage.*","com.wevioo.demande.*"})
public class Main {

	public static void main(final String[] args) {
		SpringApplication.run(Main.class, args);
	}
}
