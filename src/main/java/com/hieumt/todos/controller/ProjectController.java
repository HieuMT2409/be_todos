package com.hieumt.todos.controller;

import com.hieumt.todos.models.ProjectItem;
import com.hieumt.todos.models.TodoItem;
import com.hieumt.todos.services.ProjectService;
import com.hieumt.todos.services.TodoService;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping(value = "/v1/api/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @GetMapping
    public List<ProjectItem> findAll() throws ExecutionException, InterruptedException {
        return projectService.getAllProjects();
    }

    @GetMapping("/{id}")
    public ProjectItem getProjectById(@PathVariable String id) throws ExecutionException, InterruptedException {
        return projectService.getProjectById(id);
    }

    @PostMapping("/createProjects")
    public  String save(@Validated @NotNull @RequestBody ProjectItem projectItem) throws ExecutionException, InterruptedException {
        return projectService.saveProject(projectItem);
    }

    @PutMapping("/updateProjects/{id}")
    public String update(@PathVariable String id, @RequestBody ProjectItem projectItem) throws ExecutionException, InterruptedException {
        return projectService.updateProject(id, projectItem);
    }

    @DeleteMapping(value = "/delete/{id}")
    public String delete(@PathVariable String id) throws ExecutionException, InterruptedException {
        return projectService.deleteProject(id);
    }
}
