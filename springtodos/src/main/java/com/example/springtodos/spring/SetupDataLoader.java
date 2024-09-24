package com.example.springtodos.spring;

import com.example.springtodos.users.models.User;
import com.example.springtodos.security.privileges.models.Privilege;
import com.example.springtodos.security.roles.models.Role;
import com.example.springtodos.security.privileges.repositories.PrivilegeRepository;
import com.example.springtodos.security.roles.repositories.RoleRepository;
import com.example.springtodos.users.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private boolean alreadySetup = false;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PrivilegeRepository privilegeRepository;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (alreadySetup) {
            return;
        }

        // Create initial privileges
        List<Privilege> privileges = createPrivilegesIfNotFound();

        // Create initial roles
        List<Role> roles = createRolesIfNotFound(privileges);

        // Create admin
        User admin = userRepository.findUserByUsername("root");
        if (admin == null) {
            // Create admin user
             User.Builder builder = new User.Builder();
             admin = builder
                     .username("root")
                     .password("root")
                     .enabled(true)
                     .tokenExpired(true)
                     .roles(List.of(roles.get(0)))
                     .build();

             userRepository.save(admin);
        }


        alreadySetup = true;
    }

    @Transactional
    protected List<Privilege> createPrivilegesIfNotFound() {
        Privilege readPrivilege = privilegeRepository.findPrivilegeByName("READ_PRIVILEGE");
        Privilege writePrivilege = privilegeRepository.findPrivilegeByName("WRITE_PRIVILEGE");

        if (readPrivilege == null) {
            Privilege.Builder builder = new Privilege.Builder();
            readPrivilege = builder.name("READ_PRIVILEGE").roles(null).build();
            privilegeRepository.save(readPrivilege);
        }

        if (writePrivilege == null) {
            Privilege.Builder builder = new Privilege.Builder();
            writePrivilege = builder.name("WRITE_PRIVILEGE").roles(null).build();
            privilegeRepository.save(writePrivilege);
        }

        return List.of(readPrivilege, writePrivilege);
    }

    @Transactional
    protected List<Role> createRolesIfNotFound(List<Privilege> privileges) {
        Role adminRole = roleRepository.findRoleByName("ROLE_ADMIN");
        Role userRole = roleRepository.findRoleByName("ROLE_USER");

        if (adminRole == null) {
            Role.Builder builder = new Role.Builder();
            adminRole = builder.name("ROLE_ADMIN").privileges(privileges).build();
            roleRepository.save(adminRole);
        }

        if (userRole == null) {
            Role.Builder builder = new Role.Builder();
            userRole = builder.name("ROLE_USER").privileges(List.of(privileges.get(0))).build();
            roleRepository.save(userRole);
        }

        return List.of(adminRole, userRole);
    }
}
