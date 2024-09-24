package com.example.springtodos.models.users;

import com.example.springtodos.tasks.models.Task;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id; // primary key, which is auto-generated
    @NotNull
    @Column(nullable = false, columnDefinition = "text",
            length = 50, unique = true, updatable = false)
    @Size(min = 1, max = 50, message = "Username must be between 1 and 50 characters")
    private String username;
    @Column(nullable = false, columnDefinition = "text", length = 50, unique = true)
    private String email;
    @Column(nullable = false, columnDefinition = "text", length = 80)
    @Size(min = 1, max = 80, message = "Password must be between 1 and 80 characters")
    private String password;
    @OneToMany(mappedBy = "user")
    private List<Task> tasks;
    private boolean enabled;
    private boolean tokenExpired;

    public User() {
        // default constructor
    }

    /**
     * Constructor for creating a new user with a username, email, password, and tasks
     * @param username
     * @param email
     * @param password
     * @param tasks
     */
    public User(String username, String email, String password, List<Task> tasks) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.tasks = tasks;
        this.tokenExpired = true;
        this.enabled = false;

    }

    /**
     * Constructor for creating a new user with a username, email, password, tasks, enabled, and tokenExpired
     * @param username
     * @param email
     * @param password
     * @param tasks
     * @param enabled
     * @param tokenExpired
     */
    public User(String username, String email, String password, List<Task> tasks, boolean enabled, boolean tokenExpired) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.tokenExpired = true;
        this.enabled = false;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public boolean isTokenExpired() {
        return tokenExpired;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void setTokenExpired(boolean tokenExpired) {
        this.tokenExpired = tokenExpired;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", tasks=" + tasks +
                ", enabled=" + enabled +
                ", tokenExpired=" + tokenExpired +
                '}';
    }

    // Builder pattern
    public static class Builder {
        private String username;
        private String email;
        private String password;
        private List<Task> tasks;
        private boolean enabled = false;
        private boolean tokenExpired = true;

        public Builder() {
            // default constructor
        }

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder tasks(List<Task> tasks) {
            this.tasks = tasks;
            return this;
        }

        public Builder enabled(boolean enabled) {
            this.enabled = enabled;
            return this;
        }

        public Builder tokenExpired(boolean tokenExpired) {
            this.tokenExpired = tokenExpired;
            return this;
        }

        public User build() {
            return new User(username, email, password, tasks, enabled, tokenExpired);
        }
    }
}

