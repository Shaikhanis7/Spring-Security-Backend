package com.max.quizspring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.max.quizspring.model.User;
import com.max.quizspring.repo.UserRepo;

@Service
public class UserService {

    private final UserRepo userRepository;

    @Autowired
    public UserService(UserRepo userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Retrieve a user by their email.
     * 
     * @param email the email of the user to retrieve
     * @return the User object if found, or null if not found
     */
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    
}
