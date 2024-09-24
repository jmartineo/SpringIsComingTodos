package com.example.springtodos.security.roles.repositories;

import com.example.springtodos.users.models.User;
import com.example.springtodos.security.roles.models.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    public Role findRoleByName(String name);
    public Role findRoleById(long id);
    public Page<Role> findAll(Pageable pageable);
    public Page<Role> findRolesForUser(User user, Pageable pageable);
    public Page<User> findUsersForRole(Role role, Pageable pageable);
}
