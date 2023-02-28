package com.example.demo;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(info = @Info(title = "레데 api 명세서", description = "라이징 캠프 API 명세서", version = "v1"))
@Configuration
public class SwaggerConfig {
    @Bean
    public GroupedOpenApi chatOpenApi() {
        String[] paths = {"/**"};

        return GroupedOpenApi.builder()
                .group("레데 쿠팡 서비스 API V1")
                .pathsToMatch(paths)
                .build();
    }
}
