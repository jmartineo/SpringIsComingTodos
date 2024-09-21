package com.example.springtodos.repositories;

import com.example.springtodos.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    // This interface extends JpaRepository, which provides CRUD operations for the Task entity
    // The Task entity is the type of entity that this repository manages (Task) and the type of the primary key of the entity (Long)
    // The repository is annotated with @Repository, which indicates that it is a Spring-managed component
    // The repository does not contain any custom methods, so it inherits all the methods from JpaRepository
    // The repository is an interface, so Spring Data JPA will provide an implementation of this interface at runtime
    // The implementation will be based on the configuration in the application.properties file
    // The repository will be used by the TaskService to interact with the database

    // This method is a custom query method that finds a Task by its name
    public Task findTaskByName(String name);
    public Task findTaskById(long id);
    public List<Task> findCompleteTasks();
    public List<Task> findIncompleteTasks();
    public List<Task> findAllTasks();
}
