package com.example.notesapp.services;


import com.example.notesapp.models.Users;
import com.example.notesapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }

    public Users getUserById(Long id) {
        return userRepository.findById(id).orElse(null); // Ensure it handles missing notes
    }

    public Users createUser(Users user) {

        return userRepository.save(user);
    }


    public Users updateUser(Users user) {
        return userRepository.save(user);
    }


    public void deleteUserById(Long userId) {
        this.userRepository.deleteById(userId);
    }
}
