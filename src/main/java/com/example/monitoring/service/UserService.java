// UserService.java
package com.example.monitoring.service;

import com.example.monitoring.model.User;
import com.example.monitoring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUser(Long userId) {
        return userRepository.findById(userId);
    }

    public User addUser(User user) {
        return userRepository.save(user);
    }

    public void removeUser(Long userId) {
        userRepository.deleteById(userId);
    }
}
