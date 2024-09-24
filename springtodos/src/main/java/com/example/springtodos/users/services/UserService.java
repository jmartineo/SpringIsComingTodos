package com.example.springtodos.users.services;

import com.example.springtodos.users.models.User;
import com.example.springtodos.users.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Optional<User> createNewUser(User user) {
        return Optional.ofNullable(userRepository.save(user));
    }

    public Optional<Page<User>> getAllUsers(Pageable pageable) {
        return Optional.ofNullable(userRepository.findAll(pageable));
    }

    public Optional<User> getUserById(long id) {
        return Optional.ofNullable(userRepository.findUserById(id));
    }

    public Optional<User> getUserByUsername(String username) {
        return Optional.ofNullable(userRepository.findUserByUsername(username));
    }

    public Optional<User> updateUser(User user) {
        return Optional.ofNullable(userRepository.save(user));
    }

    public void deleteUser(User user) {
        userRepository.delete(user);
    }
}
