package com.navi.gatewayserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GatewayserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayserverApplication.class, args);
	}

	@Bean
	public RouteLocator buildRouteLocator(RouteLocatorBuilder routeLocatorBuilder){
		return routeLocatorBuilder.routes()
				.route(p -> p
						.path("/navibank/accounts/**")
						.filters(f -> f.rewritePath("/navibank/accounts/(?<segment>.*)", "/${segment}"))
						.uri("lb://ACCOUNTS"))
				.route(p -> p
						.path("/navibank/cards/**")
						.filters(f -> f.rewritePath("/navibank/cards/(?<segment>.*)", "/${segment}"))
						.uri("lb://CARDS"))
				.route(p -> p
						.path("/navibank/loans/**")
						.filters(f -> f.rewritePath("/navibank/loans/(?<segment>.*)", "/${segment}"))
						.uri("lb://LOANS"))
				.build();
	}
}
