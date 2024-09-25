package com.example.springtodos.security.roles.services;

import java.util.Optional;

import com.example.springtodos.users.models.User;
import com.example.springtodos.security.roles.models.Role;
import com.example.springtodos.security.roles.repositories.RoleRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

    public Optional<Role> createNewRole(Role role) {
        return Optional.ofNullable(roleRepository.save(role));
    }

    public Optional<Page<Role>> getAllRoles(Pageable pageable) {
        return Optional.ofNullable(roleRepository.findAll(pageable));
    }

    public Optional<Role> getRoleById(long id) {
        return Optional.ofNullable(roleRepository.findRoleById(id));
    }

    public Optional<Role> getRoleByName(String name) {
        return Optional.ofNullable(roleRepository.findRoleByName(name));
    }

    public Optional<Page<Role>> getRolesForUser(User user, Pageable pageable) {
        return Optional.ofNullable(roleRepository.findRolesByUsers(user, pageable));
    }

    public Optional<Role> updateRole(Role role) {
        return Optional.ofNullable(roleRepository.save(role));
    }

    public void deleteRole(Role role) {
        roleRepository.delete(role);
    }
}
