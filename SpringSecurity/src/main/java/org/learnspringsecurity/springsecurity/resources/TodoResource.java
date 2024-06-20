package org.learnspringsecurity.springsecurity.resources;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TodoResource {
    private Logger logger = LoggerFactory.getLogger(getClass());

    // Use ArrayList to allow modifications
    private static final List<Todo> Todos = new ArrayList<>(List.of(
            new Todo("Sarab", "Learn DevOps"),
            new Todo("Jashan", "Learn Fufua")
    ));

    @GetMapping("/todo")
    public List<Todo> retrieveAllTodos() {
        return Todos;
    }

    @GetMapping("users/{username}/todos")
    public List<Todo> retrieveUserTodos(@PathVariable String username) {
        List<Todo> userTodos = new ArrayList<>();
        for (Todo todo : Todos) {
            if (todo.username().equals(username)) {
                userTodos.add(todo);
            }
        }
        return userTodos;
    }

    @PostMapping("/users/{username}/todos")
    public void addTodo(@PathVariable String username, @RequestBody Todo todo) {
        Todos.add(todo);
        logger.info("Created new todo: {} for {}", todo, username);
    }

    // Java record for Todo
    public record Todo(String username, String description) {
    }
}
