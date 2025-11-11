package com.todo.service;

import com.todo.model.Todo;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class TodoService {
    private final String DB_PATH = "src/main/resources/db.json";
    private final ObjectMapper mapper = new ObjectMapper();
    private List<Todo> todos = new ArrayList<>();
    private int nextId = 1;

    public TodoService() {
        loadTodos();
    }

    public List<Todo> getAll() {
        return todos;
    }

    public Todo add(Todo todo) {
        todo.setId(nextId++);
        todos.add(todo);
        saveTodos();
        return todo;
    }

    public void delete(int id) {
        todos.removeIf(t -> t.getId() == id);
        saveTodos();
    }

    public void toggle(int id) {
        todos.stream()
                .filter(t -> t.getId() == id)
                .findFirst()
                .ifPresent(t -> t.setCompleted(!t.isCompleted()));
        saveTodos();
    }

    private void loadTodos() {
        try {
            File file = new File(DB_PATH);
            if (file.exists()) {
                todos = mapper.readValue(file, new TypeReference<>() {});
                nextId = todos.stream().mapToInt(Todo::getId).max().orElse(0) + 1;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveTodos() {
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(DB_PATH), todos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
