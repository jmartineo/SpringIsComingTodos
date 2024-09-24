package com.example.springtodos.security;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
public class Privilege {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id; // primary key, which is auto-generated
    private String name;
    @ManyToMany(mappedBy = "privileges")
    private Collection<Role> roles;

    public Privilege() {
        // default constructor
    }

    public Privilege(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Collection<Role> getRoles() {
        return roles;
    }
}
