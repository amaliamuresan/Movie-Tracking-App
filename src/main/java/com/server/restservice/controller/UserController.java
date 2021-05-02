package com.server.restservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.firebase.auth.FirebaseAuthException;
import com.server.restservice.models.User;
import com.server.restservice.operation.JsonOperation;
import com.server.restservice.service.UserMovieService;
import com.server.restservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserMovieService userMovieService;

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

    @PostMapping("/users/add_to_watch_movie")
    public Object addToWatchMovie(@RequestBody Map<String,String> request) throws ExecutionException, InterruptedException {
        String uid;
        String movie_id;
        String token;

        uid = request.get("uid");
        movie_id = request.get("movie_id");
        token = request.get("token");

        if(uid == null || movie_id == null || token == null) {
            return JsonOperation.createJson("Error", "Invalid request parameters");
        }

        if(!userService.checkLegitUser(uid,token)) {
            return JsonOperation.createJson("Error", "Invalid token");
        }

        return userMovieService.addToWatchMovie(uid,movie_id);
    }

    @GetMapping("/users/get_to_watch_movie")
    public Object getToWatchMovie(@RequestBody Map<String,String> request) throws ExecutionException, InterruptedException, JsonProcessingException {
        String loggedUid;
        String userUid;
        String token;

        loggedUid = request.get("logged_uid");
        userUid = request.get("user_uid");
        token = request.get("token");

        if(loggedUid == null || userUid == null || token == null) {
            return JsonOperation.createJson("Error", "Invalid request parameters");
        }

        if(!userService.checkLegitUser(loggedUid,token)) {
            return JsonOperation.createJson("Error", "Invalid token");
        }
        return userMovieService.getToWatchMovies(userUid);
    }

    @PostMapping("/users/remove_to_watch_movie")
    public Object removeToWatchMovie(@RequestBody Map<String,String> request) throws ExecutionException, InterruptedException {
        String uid;
        String movie_id;
        String token;

        uid = request.get("uid");
        movie_id = request.get("movie_id");
        token = request.get("token");

        if(uid == null || movie_id == null || token == null) {
            return JsonOperation.createJson("Error", "Invalid request parameters");
        }

        if(!userService.checkLegitUser(uid,token)) {
            return JsonOperation.createJson("Error", "Invalid token");
        }

        return userMovieService.removeMovie(uid,movie_id,"to_watch");
    }


    @PostMapping("/users/add_watched_movie")
    public Object addWatchedMovie(@RequestBody Map<String,String> request) throws ExecutionException, InterruptedException {
        String uid;
        String movie_id;
        String token;

        uid = request.get("uid");
        movie_id = request.get("movie_id");
        token = request.get("token");

        if(uid == null || movie_id == null || token == null) {
            return JsonOperation.createJson("Error", "Invalid request parameters");
        }

        if(!userService.checkLegitUser(uid,token)) {
            return JsonOperation.createJson("Error", "Invalid token");
        }

        return userMovieService.addWatchedMovie(uid,movie_id);
    }

    @GetMapping("/users/get_watched_movie")
    public Object getWatchedMovie(@RequestBody Map<String,String> request) throws ExecutionException, InterruptedException, JsonProcessingException {
        String loggedUid;
        String userUid;
        String token;

        loggedUid = request.get("logged_uid");
        userUid = request.get("user_uid");
        token = request.get("token");

        if(loggedUid == null || userUid == null || token == null) {
            return JsonOperation.createJson("Error", "Invalid request parameters");
        }

        if(!userService.checkLegitUser(loggedUid,token)) {
            return JsonOperation.createJson("Error", "Invalid token");
        }

        return userMovieService.getWatchedMovie(userUid);
    }

    @PostMapping("/users/remove_watched_movie")
    public Object removeWatchedMovie(@RequestBody Map<String,String> request) throws ExecutionException, InterruptedException {
        String uid;
        String movie_id;
        String token;

        uid = request.get("uid");
        movie_id = request.get("movie_id");
        token = request.get("token");

        if(uid == null || movie_id == null || token == null) {
            return JsonOperation.createJson("Error", "Invalid request parameters");
        }

        if(!userService.checkLegitUser(uid,token)) {
            return JsonOperation.createJson("Error", "Invalid token");
        }

        return userMovieService.removeMovie(uid,movie_id,"watched");
    }



}
