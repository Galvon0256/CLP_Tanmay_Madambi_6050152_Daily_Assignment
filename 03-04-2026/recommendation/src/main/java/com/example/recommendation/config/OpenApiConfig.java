package com.example.recommendation.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    OpenAPI openAPI(@Value("${app.public-base-url:http://localhost:8080}") String publicBaseUrl) {
        return new OpenAPI().servers(List.of(new Server().url(publicBaseUrl)));
    }
}

