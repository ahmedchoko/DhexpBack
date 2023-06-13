package com.wevioo.parametrage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.CacheControl;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.ResourceHandlerRegistry;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebHandler;
import org.springframework.web.server.handler.DefaultWebFilterChain;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.web.reactive.config.ResourceHandlerRegistry;
import org.springframework.web.reactive.resource.PathResourceResolver;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@EnableDiscoveryClient
@SpringBootApplication
@EnableWebFlux
public class ParametrageApplication {

    public static void main(String[] args) {
        SpringApplication.run(ParametrageApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    /**
     * Configuration class for WebFlux.
     * Registers a resource handler to serve static images from the "classpath:/static/" directory.
     * The resource handler is mapped to "/images/**" URL pattern.
     * Enables resource chaining and adds a PathResourceResolver to resolve the resource path.
     */
    @Configuration
    @EnableWebFlux
    public static class WebConfig implements WebFluxConfigurer {

        private final ResourcePatternResolver resourcePatternResolver;

        public WebConfig(ResourcePatternResolver resourcePatternResolver) {
            this.resourcePatternResolver = resourcePatternResolver;
        }

        @Override
        public void addResourceHandlers(ResourceHandlerRegistry registry) {
            registry.addResourceHandler("/images/**")
                    .addResourceLocations("file:/A:/sotugarPfe/dhexp/Parametrage-Service/src/main/resources/static/")
                    .resourceChain(true)
                    .addResolver(new PathResourceResolver());
        }


    }
}
