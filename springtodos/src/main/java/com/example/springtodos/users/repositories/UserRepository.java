package com.example.springtodos.users.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.springtodos.users.models.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    public User findUserByUsername(String username);
    public User findUserById(long id);
    public List<User> findAll();
}
