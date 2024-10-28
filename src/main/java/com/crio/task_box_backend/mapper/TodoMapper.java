package com.crio.task_box_backend.mapper;

import com.crio.task_box_backend.dto.TodoDTO;
import com.crio.task_box_backend.entity.Todo;

public class TodoMapper {

    public static TodoDTO convertToDto(Todo todo) {
        TodoDTO todoDTO = new TodoDTO();
        todoDTO.setTitle(todo.getTitle());
        todoDTO.setDescription(todo.getDescription());
        todoDTO.setCompleted(todo.isCompleted());
        return todoDTO;
    }

    public static Todo convertToEntity(TodoDTO todoDTO) {
        Todo todo = new Todo();
        todo.setTitle(todoDTO.getTitle());
        todo.setDescription(todoDTO.getDescription());
        todo.setCompleted(todoDTO.isCompleted());
        return todo;
    }
}