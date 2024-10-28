package com.crio.task_box_backend.controller;

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
public class TodoController {

    @Autowired
    private TodoService todoService;

    @GetMapping
    public List<TodoDTO> getAllTodos() {
        return todoService.getAllTodos().stream()
                .map(TodoMapper::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public TodoDTO getTodoById(@PathVariable Long id) {
        return TodoMapper.convertToDto(todoService.getTodoById(id));
    }

    @PostMapping
    public ResponseEntity<TodoDTO> createTodo(@Valid @RequestBody TodoDTO todoDTO) {
        Todo todo = TodoMapper.convertToEntity(todoDTO);
        Todo createdTodo = todoService.createTodo(todo);
        return new ResponseEntity<>(TodoMapper.convertToDto(createdTodo), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public TodoDTO updateTodo(@PathVariable Long id, @Valid @RequestBody TodoDTO todoDTO) {
        Todo updatedTodo = TodoMapper.convertToEntity(todoDTO);
        return TodoMapper.convertToDto(todoService.updateTodo(id, updatedTodo));
    }

    @DeleteMapping("/{id}")
    public void deleteTodo(@PathVariable Long id) {
        todoService.deleteTodo(id);
    }
}