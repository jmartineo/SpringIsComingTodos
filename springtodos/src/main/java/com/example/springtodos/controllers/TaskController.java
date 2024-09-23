package com.example.springtodos.controllers;

import com.example.springtodos.models.Task;
import com.example.springtodos.services.TaskService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.naming.NameAlreadyBoundException;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping("/")
    public ResponseEntity<Page<Task>> getAllTasks(@RequestParam int page, @RequestParam int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Task> tasks = taskService.getAllTasks(pageRequest);
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @GetMapping("/complete")
    public ResponseEntity<Page<Task>> getCompleteTasks(@RequestParam int page, @RequestParam int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return new ResponseEntity<>(taskService.getCompleteTasks(pageRequest), HttpStatus.OK);
    }

    @GetMapping("/incomplete")
    public ResponseEntity<Page<Task>> getIncompleteTasks(@RequestParam int page, @RequestParam int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return new ResponseEntity<>(taskService.getIncompleteTasks(pageRequest), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable long id) {
        Task task = taskService.getTaskById(id);
        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Task> getTaskByName(@PathVariable String name) {
        Task task = taskService.getTaskByName(name);
        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        ResponseEntity<Task> res = getTaskByName(task.getName());
        Task t = res.getBody();
        if (t != null)
            return new ResponseEntity<>(t, HttpStatus.CONFLICT);
        Task newTask = taskService.createNewTask(task);
        return new ResponseEntity<>(newTask, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable long id, @RequestBody Task task) {
        Task t = taskService.getTaskById(id);
        if (t == null)
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

        System.out.println("Task: " + task.toString());
        if (task.getName() != null)
            t.setName(task.getName());
        if (task.getDescription() != null)
            t.setDescription(task.getDescription());
        if (task.isCompleted() != t.isCompleted())
            t.setCompleted(task.isCompleted());
        Task updatedTask = taskService.updateTask(t);

        return new ResponseEntity<>(updatedTask, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable long id) {
        Task task = taskService.getTaskById(id);
        if (task == null)
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        taskService.deleteTask(task);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
}
