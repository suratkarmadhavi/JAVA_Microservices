package com.api_gateway.config;

import java.util.List;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.NimbusReactiveJwtDecoder;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.web.server.SecurityWebFilterChain;
import io.jsonwebtoken.security.Keys;
import reactor.core.publisher.Mono;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverterAdapter;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.oauth2.jwt.Jwt;
import reactor.core.publisher.Mono;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain securityFilterChain(ServerHttpSecurity http) {
        return http
            .csrf(csrf -> csrf.disable())
            .authorizeExchange(exchange -> exchange
                // Public endpoints
                .pathMatchers("/users/registerUser").permitAll()
                // Authenticated endpoints
                .pathMatchers("/products/**").hasRole("USER")
                .pathMatchers("/orders/**").authenticated()
                .pathMatchers("/payment/**").authenticated()
                .anyExchange().permitAll()
            )
            .oauth2ResourceServer(oauth2 -> oauth2
                .jwt(jwt -> jwt
                    .jwtAuthenticationConverter(keycloakJwtConverter())
                )
            )
            .build();
    }

    @Bean
    public Converter<Jwt, Mono<AbstractAuthenticationToken>> keycloakJwtConverter() {
        return new KeycloakJwtAuthenticationConverter();
    }
}
//
//@Configuration
//@EnableWebFluxSecurity
//@EnableMethodSecurity
//public class SecurityConfig {
//
//    @Value("${jwt.secret}")
//    private String secret;
//
//    @Bean
//    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
//
//        return http
//                .csrf(ServerHttpSecurity.CsrfSpec::disable)
//                .authorizeExchange(exchange -> exchange
//                		.pathMatchers("/auth/login").permitAll()
//                		.pathMatchers("/users/registerUser").permitAll()
//                        .anyExchange().authenticated()
//                )
//                .oauth2ResourceServer(oauth -> oauth
//                        .jwt(jwtSpec -> jwtSpec
//                                .jwtDecoder(jwtDecoder())
//                                .jwtAuthenticationConverter(token -> {
//
//                                    String role = token.getClaim("role");
//
//                                    return Mono.just(
//                                            new JwtAuthenticationToken(
//                                                    token,
//                                                    List.of(new SimpleGrantedAuthority("ROLE_" + role))
//                                            )
//                                    );
//                                })
//                        )
//                )
//                .build();
//    }
//
//    @Bean
//    public ReactiveJwtDecoder jwtDecoder() {
//        SecretKey key = Keys.hmacShaKeyFor(secret.getBytes());
//        return NimbusReactiveJwtDecoder.withSecretKey(key).build();
//    }
//}

//@Configuration
//@EnableWebFluxSecurity
//public class SecurityConfig {
//
//	@Bean
//	public SecurityWebFilterChain securityFilterChain(ServerHttpSecurity http) {
//
//		return http.csrf(csrf -> csrf.disable())
//				.authorizeExchange(exchange -> exchange.pathMatchers("/product/**").authenticated()
//						.pathMatchers("/order/**").authenticated().anyExchange().permitAll())
//				.oauth2ResourceServer(oauth2 -> oauth2.jwt()).build();
//	}
//}
