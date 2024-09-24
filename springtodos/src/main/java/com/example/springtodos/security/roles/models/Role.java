package com.example.springtodos.security.roles.models;

import com.example.springtodos.users.models.User;
import com.example.springtodos.security.privileges.models.Privilege;
import jakarta.persistence.*;

import java.util.Collection;

@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id; // primary key, which is auto-generated
    private String name;
    @ManyToMany(mappedBy = "roles")
    private Collection<User> users;

    @ManyToMany
    @JoinTable(
            name = "roles_privileges",
            joinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "privilege_id", referencedColumnName = "id"))
    private Collection<Privilege> privileges;

    public Role() {
        // default constructor
    }

    public Role(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Collection<User> getUsers() {
        return users;
    }

    public Collection<Privilege> getPrivileges() {
        return privileges;
    }

    public static class Builder {
        private Long id;
        private String name;
        private Collection<User> users;
        private Collection<Privilege> privileges;

        public Builder() {
            // default constructor
        }

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder users(Collection<User> users) {
            this.users = users;
            return this;
        }

        public Builder privileges(Collection<Privilege> privileges) {
            this.privileges = privileges;
            return this;
        }

        public Role build() {
            Role role = new Role();
            role.id = this.id;
            role.name = this.name;
            role.users = this.users;
            role.privileges = this.privileges;
            return role;
        }
    }
}
