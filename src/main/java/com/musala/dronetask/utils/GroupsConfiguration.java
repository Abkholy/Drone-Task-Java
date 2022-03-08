package com.musala.dronetask.utils;


import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.GroupedOpenApi;
import org.springdoc.core.SwaggerUiConfigParameters;
import org.springdoc.core.SwaggerUiOAuthProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ org.springdoc.core.SpringDocConfigProperties.class,
        org.springdoc.webmvc.core.MultipleOpenApiSupportConfiguration.class,
        org.springdoc.core.SpringDocConfiguration.class, org.springdoc.webmvc.core.SpringDocWebMvcConfiguration.class,
        SwaggerUiConfigParameters.class, SwaggerUiOAuthProperties.class,
        org.springdoc.core.SwaggerUiConfigProperties.class, org.springdoc.core.SwaggerUiOAuthProperties.class,
        org.springdoc.webmvc.ui.SwaggerConfig.class,
        org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration.class })
public class GroupsConfiguration {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().components(new Components()).info(new Info().version("1.0.0").title("Drones Task").description("Abdelrahman Alkholy"));
    }

    @Bean
    public GroupedOpenApi privateApi() {
        return GroupedOpenApi.builder().group("Drones Task").pathsToMatch("/api/**").build();
    }



}