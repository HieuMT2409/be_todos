package com.hieumt.todos.controller;

import com.hieumt.todos.models.TodoItem;
import com.hieumt.todos.services.TodoService;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping(value = "/v1/api/todos")
public class TodoController {

    @Autowired
    private TodoService todoService;

    @GetMapping
    public List<TodoItem> findAll() throws ExecutionException, InterruptedException {
        return todoService.getAllTodos();
    }

    @GetMapping("/{id}")
    public TodoItem getTodoById(@PathVariable String id) throws ExecutionException, InterruptedException {
        return todoService.getTodoById(id);
    }

    @PostMapping("/createTodos")
    public  String save(@Validated @NotNull @RequestBody TodoItem todoItem) throws ExecutionException, InterruptedException {
        return todoService.saveTodo(todoItem);
    }

    @PutMapping("/updateTodos/{id}")
    public String update(@PathVariable String id, @RequestBody TodoItem todoItem) throws ExecutionException, InterruptedException {
        return todoService.updateTodo(id, todoItem);
    }

    @DeleteMapping(value = "/delete/{id}")
    public String delete(@PathVariable String id) throws ExecutionException, InterruptedException {
        return todoService.deleteTodo(id);
    }
}
