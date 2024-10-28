package com.crio.task_box_backend.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI taskBoxOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("Task Box API")
                .description("REST API for managing tasks and to-do items")
                .version("1.0")
                .contact(new Contact()
                    .name("Your Name")
                    .email("your.email@example.com")))
            .servers(List.of(
                new Server()
                    .url("http://localhost:8080")
                    .description("Local Development Server")
            ));
    }
}


