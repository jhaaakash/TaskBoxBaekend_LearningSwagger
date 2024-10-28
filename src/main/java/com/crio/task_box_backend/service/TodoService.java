// TodoService.java (Interface)

package com.crio.task_box_backend.service;

import java.util.List;

import com.crio.task_box_backend.entity.Todo;

public interface TodoService {
    Todo createTodo(Todo todo);
    List<Todo> getAllTodos();
    Todo getTodoById(Long id);
    Todo updateTodo(Long id, Todo todo);
    void deleteTodo(Long id);
}