package com.crio.task_box_backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class TodoDTO {

    @NotBlank(message = "Title is mandatory")
    @Size(min = 3, message = "Title should be at least 3 characters long")
    private String title;

    private String description;
    private boolean completed;

    public TodoDTO() {
    }

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
