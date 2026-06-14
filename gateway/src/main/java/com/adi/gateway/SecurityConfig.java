package com.adi.gateway;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {
    @Bean
    public SecurityWebFilterChain getSecurityWebFilterChain(ServerHttpSecurity http){
        return http
                .csrf(csrf-> csrf.disable())
                .authorizeExchange(exchange->exchange.anyExchange().authenticated())
                .oauth2ResourceServer(oauth->oauth.jwt(Customizer.withDefaults())).build();
    }

    @Bean
    public CorsConfigurationSource getCorsConfigurationSource(){
        CorsConfiguration corsConfiguration=new CorsConfiguration();
        corsConfiguration.setAllowedMethods(Arrays.asList("GET","POST","PATCH","DELETE","PUT","OPTIONS"));
        corsConfiguration.setAllowedHeaders(Arrays.asList("Authorization","Content-Type","X-USER-ID"));
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setAllowedOrigins(List.of("http://localhost:5173"));
        UrlBasedCorsConfigurationSource corsConfigurationSource=new UrlBasedCorsConfigurationSource();
        corsConfigurationSource.registerCorsConfiguration("/api/**",corsConfiguration);
        return corsConfigurationSource;
    }
}
