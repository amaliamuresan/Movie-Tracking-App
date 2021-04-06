package com.server.restservice.controller;

import com.google.firebase.auth.FirebaseAuthException;
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

    @PostMapping("/users/register")
    public Object saveUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @PostMapping("/users/login")
    public Object loginUser(@RequestBody User user) throws ExecutionException, InterruptedException, FirebaseAuthException {
        return userService.loginUser(user);
    }

    @GetMapping("/users/{name}")
    public User getDetails(@PathVariable String name) throws ExecutionException, InterruptedException {
        return userService.getUserDetails(name);
    }

    @PutMapping("/users/update/{token}")
    public Object update(@PathVariable String token, @RequestBody User user) throws ExecutionException, InterruptedException {
        return userService.updateUser(user,token);
    }
}
