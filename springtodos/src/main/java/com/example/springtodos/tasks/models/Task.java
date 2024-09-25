package com.example.springtodos.tasks.models;

import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import com.example.springtodos.users.models.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // primary key, which is auto-generated
    @NotNull
    @Column(nullable = false, columnDefinition = "text", 
            length = 150)
    @Size(min = 1, max = 150, message = "Name must be between 1 and 150 characters")
    private String name;
    @Column(nullable = false, columnDefinition = "text")
    private String description;
    @NotNull
    @Column(nullable = false, columnDefinition = "boolean default false")
    private boolean completed;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false) // Foreign key column
    private User user;

    public Task() {
        // default constructor
    }

    public Task(String name, String description, boolean completed, User user) {
        this.name = name;
        this.description = description;
        this.completed = completed;
        this.user = user;
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

    public User getUser() {
        return user;
    }

    public boolean isCompleted() {
        return completed;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", completed=" + completed +
                ", user=" + user +
                '}';
    }

    public static class Builder {
        private String name;
        private String description;
        private boolean completed;
        private User user;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder completed(boolean completed) {
            this.completed = completed;
            return this;
        }

        public Builder user(User user) {
            this.user = user;
            return this;
        }

        public Task build() {
            return new Task(name, description, completed, user);
        }
    }
}
