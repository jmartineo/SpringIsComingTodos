package com.example.springtodos.models;

import jakarta.persistence.*;

@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // primary key, which is auto-generated
    private String name;
    private String description;
    private boolean completed;

    public Task() {
        // default constructor
    }

    public Task(String name, String description, boolean completed) {
        this.name = name;
        this.description = description;
        this.completed = completed;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", completed=" + completed +
                '}';
    }
}
