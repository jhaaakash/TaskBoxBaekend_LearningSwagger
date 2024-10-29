package com.crio.task_box_backend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(description = "Data Transfer Object for Todo operations")
public class TodoDTO {

    @Schema(
        description = "Title of the todo item",
        example = "Complete Java Assignment",
        required = true,
        minLength = 3
    )
    @NotBlank(message = "Title is mandatory")
    @Size(min = 3, message = "Title should be at least 3 characters long")
    private String title;

    @Schema(
        description = "Detailed description of the todo item",
        example = "Complete the Spring Boot assignment including Swagger documentation",
        required = false
    )
    private String description;

    @Schema(
        description = "Completion status of the todo item",
        example = "false",
        defaultValue = "false"
    )
    private boolean completed;

    // Default constructor
    public TodoDTO() {
    }

    // Parameterized constructor
    public TodoDTO(String title, String description, boolean completed) {
        this.title = title;
        this.description = description;
        this.completed = completed;
    }

    // Getters and Setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
