package com.itnexusglobal.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.ReactiveDiscoveryClient;
import org.springframework.cloud.gateway.discovery.DiscoveryClientRouteDefinitionLocator;
import org.springframework.cloud.gateway.discovery.DiscoveryLocatorProperties;
import org.springframework.cloud.gateway.route.RouteDefinitionLocator;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}
//	@Bean
	public RouteLocator routes(RouteLocatorBuilder builder) {
		return  builder.routes()
				.route(r->r.path("/persons/**").uri("lb://PERSON-SERVICE"))
				.route(r->r.path("/api/roles/**").uri("lb://PERSON-SERVICE"))
				.route(r->r.path("/api/profiles/**").uri("lb://PROFILE-SERVICE"))
				.build();
	}
	@Bean
	public DiscoveryClientRouteDefinitionLocator dynamicRoutes(ReactiveDiscoveryClient rdc, DiscoveryLocatorProperties dlp) {
		return new DiscoveryClientRouteDefinitionLocator(rdc,dlp);
	}


}
