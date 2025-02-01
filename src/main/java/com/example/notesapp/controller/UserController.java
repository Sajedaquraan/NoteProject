package com.example.notesapp.controller;

import com.example.notesapp.models.Users;
import com.example.notesapp.services.NoteService;
import com.example.notesapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/User")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping
    public List<Users> getAllUsers() {

        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public Users getUserById(@PathVariable Long id) {

        return userService.getUserById(id);
    }

    @PostMapping("/create")
    public Users createUser(@RequestBody Users user) {

        return userService.createUser(user);
    }

    @PostMapping("/update")
    public Users updateUser(@RequestBody Users user) {

        return userService.updateUser(user);
    }


    @DeleteMapping("/delete/{userId}")
    public void deleteUserById(@PathVariable Long userId) {
        userService.deleteUserById(userId);
    }



}
