package com.example.springtodos.security.privileges.repositories;

import com.example.springtodos.security.privileges.models.Privilege;
import com.example.springtodos.security.roles.models.Role;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


@Repository
public interface PrivilegeRepository extends JpaRepository<Privilege, Long> {
    public Privilege findPrivilegeByName(String name);
    public Privilege findPrivilegeById(long id);
    public Page<Privilege> findAll(Pageable pageable);
    public Page<Privilege> findPrivilegesForRole(Role role, Pageable pageable);
    public Page<Role> findRolesForPrivilege(Privilege privilege, Pageable pageable);
}
