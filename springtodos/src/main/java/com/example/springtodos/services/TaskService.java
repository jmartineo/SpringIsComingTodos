package com.example.springtodos.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.example.springtodos.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.springtodos.models.Task;

import java.util.List;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    public Task createNewTask(Task task) {
        return taskRepository.save(task);
    }

    public Page<Task> getAllTasks(Pageable pageable) {
        return taskRepository.findAll(pageable);
    }

    public Page<Task> getCompleteTasks(Pageable pageable) {
        return taskRepository.findByCompletedTrue(pageable);
    }

    public Page<Task> getIncompleteTasks(Pageable pageable) {
        return taskRepository.findByCompletedFalse(pageable);
    }

    public Task getTaskById(long id) {
        return taskRepository.findTaskById(id);
    }

    public Task getTaskByName(String name) {
        return taskRepository.findTaskByName(name);
    }

    public Task updateTask(Task task) {
        return taskRepository.save(task);
    }

    public void deleteTask(Task task) {
        taskRepository.delete(task);
    }
}
