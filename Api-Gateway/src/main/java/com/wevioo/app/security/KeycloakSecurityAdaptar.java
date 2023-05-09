package com.wevioo.app.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
@Configuration
@EnableWebFluxSecurity
public class KeycloakSecurityAdaptar {
	  @Bean
	    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
	        http.authorizeExchange(exchanges -> exchanges.anyExchange().authenticated())
	                .oauth2ResourceServer().jwt();
	        http.csrf().disable();
	        return http.build();
	    }
}