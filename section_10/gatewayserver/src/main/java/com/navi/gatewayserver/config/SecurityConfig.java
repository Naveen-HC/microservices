package com.navi.gatewayserver.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverterAdapter;
import org.springframework.security.web.server.SecurityWebFilterChain;
import reactor.core.publisher.Mono;


@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity serverHttpSecurity){

             serverHttpSecurity.authorizeExchange(authorizeExchangeSpec ->
                    authorizeExchangeSpec.pathMatchers(HttpMethod.GET).permitAll()
                            .pathMatchers("/navibank/accounts/**").hasRole("ACCOUNTS")
                            .pathMatchers("/navibank/cards/**").hasRole("CARDS")
                            .pathMatchers("/navibank/loans/**").hasRole("LOANS"))
                     .oauth2ResourceServer(oAuth2ResourceServerSpec ->
                             oAuth2ResourceServerSpec.jwt(jwtSpec ->
                                     jwtSpec.jwtAuthenticationConverter(grantedAuthoritiesConverter())));

            serverHttpSecurity.csrf(csrfSpec -> csrfSpec.disable());

            return serverHttpSecurity.build();
    }

    public Converter<Jwt, Mono<AbstractAuthenticationToken>> grantedAuthoritiesConverter(){

        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(new KeylockRoleConverter());

        return new ReactiveJwtAuthenticationConverterAdapter(jwtAuthenticationConverter);
    }
}