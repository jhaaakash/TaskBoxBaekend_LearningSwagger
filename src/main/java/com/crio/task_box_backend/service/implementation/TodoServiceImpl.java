// TodoServiceImpl.java (Implementation)

package com.crio.task_box_backend.service.implementation;

import com.crio.task_box_backend.entity.Todo;
import com.crio.task_box_backend.exception.TodoNotFoundException;
import com.crio.task_box_backend.service.TodoService;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class TodoServiceImpl implements TodoService {

    private final List<Todo> todos = new ArrayList<>();
    private final AtomicLong idCounter = new AtomicLong();

    @Override
    public Todo createTodo(Todo todo) {
        todo.setId(idCounter.incrementAndGet());
        todos.add(todo);
        return todo;
    }

    @Override
    public List<Todo> getAllTodos() {
        return todos;
    }

    @Override
    public Todo getTodoById(Long id) {
        return todos.stream()
                .filter(todo -> todo.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new TodoNotFoundException("Todo not found with id: " + id));
    }

    @Override
    public Todo updateTodo(Long id, Todo updatedTodo) {
        Todo todo = getTodoById(id);
        todo.setTitle(updatedTodo.getTitle());
        todo.setDescription(updatedTodo.getDescription());
        todo.setCompleted(updatedTodo.isCompleted());
        return todo;
    }

    @Override
    public void deleteTodo(Long id) {
        todos.removeIf(todo -> todo.getId().equals(id));
    }
}