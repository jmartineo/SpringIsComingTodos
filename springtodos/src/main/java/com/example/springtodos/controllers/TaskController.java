package com.example.springtodos.controllers;

import java.util.Optional;
import java.util.Set;

import com.example.springtodos.models.Task;
import com.example.springtodos.services.TaskService;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping("/")
    public ResponseEntity<Page<Task>> getAllTasks(@RequestParam int page, @RequestParam int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return taskService.getAllTasks(pageRequest)
            .map(t -> new ResponseEntity<>(t, HttpStatus.OK))
            .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    @GetMapping("/complete")
    public ResponseEntity<Page<Task>> getCompleteTasks(@RequestParam int page, @RequestParam int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return taskService.getCompleteTasks(pageRequest)
            .map(t -> new ResponseEntity<>(t, HttpStatus.OK))
            .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    @GetMapping("/incomplete")
    public ResponseEntity<Page<Task>> getIncompleteTasks(@RequestParam int page, @RequestParam int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return taskService.getIncompleteTasks(pageRequest)
            .map(t -> new ResponseEntity<>(t, HttpStatus.OK))
            .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable long id) {
       return taskService.getTaskById(id)
              .map(t -> new ResponseEntity<>(t, HttpStatus.OK))
              .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Task> getTaskByName(@PathVariable String name) {
        return taskService.getTaskByName(name)
            .map(t -> new ResponseEntity<>(t, HttpStatus.OK))
            .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    @PostMapping("/")
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Task>> violations = validator.validate(task);
        if (!violations.isEmpty())
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        
        Optional<Task> checkTask = taskService.getTaskByName(task.getName());

        if (checkTask.isPresent())
            return new ResponseEntity<>(checkTask.get(), HttpStatus.CONFLICT);
        Optional<Task> newTask = taskService.createNewTask(task);
        if (newTask.isEmpty())
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(newTask.get(), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable long id, @RequestBody Task task) {
        return taskService.getTaskById(id)
            .map(t -> {
                if (task.getName() != null) t.setName(task.getName());
                if (task.getDescription() != null) t.setDescription(task.getDescription());
                if (task.isCompleted() != t.isCompleted()) t.setCompleted(task.isCompleted());

                Task updatedTask = taskService.updateTask(t).get();
                return new ResponseEntity<>(updatedTask, HttpStatus.OK);
            }).orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable long id) {
        return taskService.getTaskById(id)
            .map(t -> {
                taskService.deleteTask(t);
                return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
            }).orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }
}
