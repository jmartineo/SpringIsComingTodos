package com.example.springtodos.repositories;

import com.example.springtodos.models.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    public Task findTaskByName(String name);
    public Task findTaskById(long id);
    public Page<Task> findByCompletedTrue(Pageable pageable);
    public Page<Task> findByCompletedFalse(Pageable pageable);
    public Page<Task> findAll(Pageable pageable);
}
