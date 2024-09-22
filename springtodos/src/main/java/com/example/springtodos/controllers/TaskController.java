package com.example.springtodos.controllers;

import com.example.springtodos.models.Task;
import com.example.springtodos.services.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

//    @GetMapping("/")
//    public ResponseEntity<List<Task>> getAllTasks() {
//        System.out.println("Get all tasks");
//        List<Task> tasks = taskService.getAllTasks();
//        return new ResponseEntity<>(tasks, HttpStatus.OK);
//    }
//
//    @GetMapping("/complete")
//    public ResponseEntity<List<Task>> getCompleteTasks() {
//        List<Task> tasks = taskService.getCompleteTasks();
//        return new ResponseEntity<>(tasks, HttpStatus.OK);
//    }
//
//    @GetMapping("/incomplete")
//    public ResponseEntity<List<Task>> getIncompleteTasks() {
//        List<Task> tasks = taskService.getIncompleteTasks();
//        return new ResponseEntity<>(tasks, HttpStatus.OK);
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<Task> getTaskById(@PathVariable long id) {
//        Task task = taskService.getTaskById(id);
//        return new ResponseEntity<>(task, HttpStatus.OK);
//    }
//
//    @PostMapping("/")
//    public ResponseEntity<Task> createTask(@RequestBody Task task) {
//        Task newTask = taskService.createNewTask(task);
//        return new ResponseEntity<>(newTask, HttpStatus.CREATED);
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<Task> updateTask(@PathVariable long id, @RequestBody Task task) {
//        Task updatedTask = taskService.updateTask(task);
//        return new ResponseEntity<>(updatedTask, HttpStatus.OK);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteTask(@PathVariable long id) {
//        Task task = taskService.getTaskById(id);
//        taskService.deleteTask(task);
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }
//
//
    @GetMapping("/")
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping("/complete")
    public List<Task> getCompleteTasks() {
        return taskService.getCompleteTasks();
    }

    @GetMapping("/incomplete")
    public List<Task> getIncompleteTasks() {
        return taskService.getIncompleteTasks();
    }

    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable long id) {
        return taskService.getTaskById(id);
    }

    @GetMapping("/name/{name}")
    public Task getTaskByName(@PathVariable String name) {
        return taskService.getTaskByName(name);
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public Task createTask(@RequestBody Task task) {
        Task t = getTaskByName(task.getName());
        if (t != null) {
            throw new IllegalArgumentException("Task with name " + task.getName() + " already exists");
        }
        
        return taskService.createNewTask(task);
    }

    @PutMapping("/{id}")
    public Task updateTask(@PathVariable long id, @RequestBody Task task) {
        return taskService.updateTask(task);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable long id) {
        Task task = taskService.getTaskById(id);
        taskService.deleteTask(task);
    }
}
