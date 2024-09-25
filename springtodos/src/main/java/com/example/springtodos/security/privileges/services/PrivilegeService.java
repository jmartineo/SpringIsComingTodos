package com.example.springtodos.security.privileges.services;

import com.example.springtodos.security.privileges.models.Privilege;
import com.example.springtodos.security.privileges.repositories.PrivilegeRepository;
import com.example.springtodos.security.roles.models.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@Service
public class PrivilegeService {
    @Autowired
    private PrivilegeRepository privilegeRepository;

    public Optional<Privilege> createNewPrivilege(Privilege privilege) {
        return Optional.ofNullable(privilegeRepository.save(privilege));
    }

    public Optional<Page<Privilege>> getAllPrivileges(Pageable pageable) {
        return Optional.ofNullable(privilegeRepository.findAll(pageable));
    }

    public Optional<Privilege> getPrivilegeById(long id) {
        return Optional.ofNullable(privilegeRepository.findPrivilegeById(id));
    }

    public Optional<Privilege> getPrivilegeByName(String name) {
        return Optional.ofNullable(privilegeRepository.findPrivilegeByName(name));
    }

    public Optional<Privilege> updatePrivilege(Privilege privilege) {
        return Optional.ofNullable(privilegeRepository.save(privilege));
    }

    public void deletePrivilege(Privilege privilege) {
        privilegeRepository.delete(privilege);
    }

    public Optional<Page<Privilege>> getPrivilegesByRole(Role role, Pageable pageable) {
        return Optional.ofNullable(privilegeRepository.findPrivilegesByRoles(role, pageable));
    }
}
