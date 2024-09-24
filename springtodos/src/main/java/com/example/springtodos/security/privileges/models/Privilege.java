package com.example.springtodos.security.privileges.models;

import com.example.springtodos.security.roles.models.Role;
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

    public static class Builder {
        private String name;
        private Collection<Role> roles;

        public Builder() {
            // default constructor
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder roles(Collection<Role> roles) {
            this.roles = roles;
            return this;
        }

        public Privilege build() {
            Privilege privilege = new Privilege();
            privilege.name = this.name;
            privilege.roles = this.roles;
            return privilege;
        }
    }
}
