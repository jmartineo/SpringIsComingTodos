package com.example.springtodos.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.example.springtodos.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.springtodos.models.Task;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    public Optional<Task> createNewTask(Task task) {
        return Optional.ofNullable(taskRepository.save(task));
    }

    public Optional<Page<Task>> getAllTasks(Pageable pageable) {
        return Optional.ofNullable(taskRepository.findAll(pageable));
    }

    public Optional<Page<Task>> getCompleteTasks(Pageable pageable) {
        return Optional.ofNullable(taskRepository.findByCompletedTrue(pageable));
    }

    public Optional<Page<Task>> getIncompleteTasks(Pageable pageable) {
        return Optional.ofNullable(taskRepository.findByCompletedFalse(pageable));
    }

    public Optional<Task> getTaskById(long id) {
        return Optional.ofNullable(taskRepository.findTaskById(id));
    }

    public Optional<Task> getTaskByName(String name) {
        return Optional.ofNullable(taskRepository.findTaskByName(name));
    }

    public Optional<Task> updateTask(Task task) {
        return Optional.ofNullable(taskRepository.save(task));
    }

    public void deleteTask(Task task) {
        taskRepository.delete(task);
    }
}
