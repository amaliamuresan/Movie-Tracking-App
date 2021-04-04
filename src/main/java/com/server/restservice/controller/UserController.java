package com.server.restservice.controller;

import com.server.restservice.models.User;
import com.server.restservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/users")
    public String saveUser(@RequestBody User user) throws ExecutionException, InterruptedException {
        return userService.saveUser(user);
    }

    @GetMapping("/users/{name}")
    public User getDetails(@PathVariable String name) throws ExecutionException, InterruptedException {
        return userService.getUserDetails(name);
    }

    @PutMapping("/users")
    public String update(@RequestBody User user) throws ExecutionException, InterruptedException {
        return userService.updateUser(user);
    }
}
