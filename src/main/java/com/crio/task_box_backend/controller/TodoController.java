package com.crio.task_box_backend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.crio.task_box_backend.dto.TodoDTO;
import com.crio.task_box_backend.entity.Todo;
import com.crio.task_box_backend.mapper.TodoMapper;
import com.crio.task_box_backend.service.TodoService;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/todos")
@Tag(name = "Todo", description = "Todo management APIs")
public class TodoController {

    @Autowired
    private TodoService todoService;

    @Operation(
        summary = "Get all todos",
        description = "Retrieves a list of all todo items"
    )
    @ApiResponse(
        responseCode = "200",
        description = "Successfully retrieved all todos",
        content = @Content(mediaType = "application/json", schema = @Schema(implementation = TodoDTO.class))
    )
    @GetMapping
    public List<TodoDTO> getAllTodos() {
        return todoService.getAllTodos().stream()
            .map(TodoMapper::convertToDto)
            .collect(Collectors.toList());
    }

    @Operation(
        summary = "Get a todo by ID",
        description = "Retrieves a specific todo item by its ID"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Todo found",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = TodoDTO.class))
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Todo not found",
            content = @Content
        )
    })
    @GetMapping("/{id}")
    public TodoDTO getTodoById(
        @Parameter(description = "ID of the todo to retrieve") @PathVariable Long id
    ) {
        return TodoMapper.convertToDto(todoService.getTodoById(id));
    }

    @Operation(
        summary = "Create a new todo",
        description = "Creates a new todo item"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "201",
            description = "Todo created successfully",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = TodoDTO.class))
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Invalid input",
            content = @Content
        )
    })
    @PostMapping
    public ResponseEntity<TodoDTO> createTodo(
        @Parameter(description = "Todo to create") @Valid @RequestBody TodoDTO todoDTO
    ) {
        Todo todo = TodoMapper.convertToEntity(todoDTO);
        Todo createdTodo = todoService.createTodo(todo);
        return new ResponseEntity<>(TodoMapper.convertToDto(createdTodo), HttpStatus.CREATED);
    }

    @Operation(
        summary = "Update a todo",
        description = "Updates an existing todo item by ID"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Todo updated successfully",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = TodoDTO.class))
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Todo not found",
            content = @Content
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Invalid input",
            content = @Content
        )
    })
    @PutMapping("/{id}")
    public TodoDTO updateTodo(
        @Parameter(description = "ID of the todo to update") @PathVariable Long id,
        @Parameter(description = "Updated todo details") @Valid @RequestBody TodoDTO todoDTO
    ) {
        Todo updatedTodo = TodoMapper.convertToEntity(todoDTO);
        return TodoMapper.convertToDto(todoService.updateTodo(id, updatedTodo));
    }

    @Operation(
        summary = "Delete a todo",
        description = "Deletes a todo item by ID"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "204",
            description = "Todo deleted successfully",
            content = @Content
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Todo not found",
            content = @Content
        )
    })
    @DeleteMapping("/{id}")
    public void deleteTodo(
        @Parameter(description = "ID of the todo to delete") @PathVariable Long id
    ) {
        todoService.deleteTodo(id);
    }
}
