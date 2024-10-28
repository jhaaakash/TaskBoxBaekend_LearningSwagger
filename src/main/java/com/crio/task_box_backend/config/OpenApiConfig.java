package com.crio.task_box_backend.config;

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


