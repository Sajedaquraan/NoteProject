package com.example.notesapp.services;


import com.example.notesapp.models.Users;
import com.example.notesapp.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class UserService {

    private final UserRepository userRepository;
    public UserService(UserRepository userRepository)
    {
        this.userRepository=userRepository;
    }

    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }

    public Users getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
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
