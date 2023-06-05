package com.wevioo.parametrage;

import java.time.Duration;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.CacheControl;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.ResourceHandlerRegistry;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.reactive.resource.PathResourceResolver;


@EnableDiscoveryClient
@SpringBootApplication
@EnableWebFlux
@EnableJpaRepositories(basePackages = {"com.wevioo.parametrage.*","com.wevioo.demande.*"})
@ComponentScan(basePackages = {"com.wevioo.parametrage.*","com.wevioo.demande.*"})
@EntityScan(basePackages = {"com.wevioo.parametrage.*","com.wevioo.demande.*"})
public class ParametrageApplication {
	public static void main(String[] args) {
		SpringApplication.run(ParametrageApplication.class, args);
	}
	 @Bean
	public ModelMapper modelMapper() {
	   return new ModelMapper();
	}
	 @Configuration
	    @EnableWebFlux
	    public static class WebConfig implements WebFluxConfigurer {

	        @Override
	        public void addResourceHandlers(ResourceHandlerRegistry registry) {
	            registry.addResourceHandler("/images/**")
	                    .addResourceLocations("classpath:/static/")
	                    .setCacheControl(CacheControl.maxAge(Duration.ofDays(1)))
	                    .resourceChain(true)
	                    .addResolver(new PathResourceResolver());
	        }
	    }
}
