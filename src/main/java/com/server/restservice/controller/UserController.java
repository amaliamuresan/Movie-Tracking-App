package com.server.restservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.firebase.auth.FirebaseAuthException;
import com.server.restservice.models.User;
import com.server.restservice.operation.JsonOperation;
import com.server.restservice.service.UserFollowService;
import com.server.restservice.service.UserMovieService;
import com.server.restservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
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
        if(user.getEmail() == null || user.getPassword() == null || user.getDisplay_name() == null) {
            return JsonOperation.createJson("Error", "Invalid request parameters");
        }
        return userService.saveUser(user);
    }

    @PostMapping("/users/login")
    public Object loginUser(@RequestBody User user) throws ExecutionException, InterruptedException, FirebaseAuthException {
        return userService.loginUser(user);
    }

    @GetMapping("/users/{name}")
    public User getDetails(@PathVariable String name) throws ExecutionException, InterruptedException {
        User user = userService.getUserDetails(name);
        user.setPassword(null);
        return user;
    }

    @PostMapping("/users/update")
    public Object update(@RequestBody Map<String,String> request) throws ExecutionException, InterruptedException {
        String uid;
        String password;
        String displayName;
        String token;
        Map<String,Object> newData = new HashMap<>();

        uid = request.get("uid");
        token = request.get("token");
        password = request.get("password");
        displayName = request.get("display_name");

        if((password == null && displayName == null) || uid == null || token == null) {
            return JsonOperation.createJson("Error", "Invalid request parameters");
        }

        String checkResponse = userService.checkLegitUser(uid,token);
        if(!checkResponse.equals("Success")) {
            return JsonOperation.createJson("Error", checkResponse);
        }

        if(password != null){
            newData.put("password",password);
        }

        if(displayName != null) {
            newData.put("display_name", displayName);
        }

        return userService.updateUser(uid, newData);
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

        String checkResponse = userService.checkLegitUser(uid,token);
        if(!checkResponse.equals("Success")) {
            return JsonOperation.createJson("Error", checkResponse);
        }

        return userMovieService.addToWatchMovie(uid,movie_id);
    }

    @PostMapping("/users/get_to_watch_movie")
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

        String checkResponse = userService.checkLegitUser(loggedUid,token);
        if(!checkResponse.equals("Success")) {
            return JsonOperation.createJson("Error", checkResponse);
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

        String checkResponse = userService.checkLegitUser(uid,token);
        if(!checkResponse.equals("Success")) {
            return JsonOperation.createJson("Error", checkResponse);
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

        String checkResponse = userService.checkLegitUser(uid,token);
        if(!checkResponse.equals("Success")) {
            return JsonOperation.createJson("Error", checkResponse);
        }

        return userMovieService.addWatchedMovie(uid,movie_id);
    }

    @PostMapping("/users/get_watched_movie")
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

        String checkResponse = userService.checkLegitUser(loggedUid,token);
        if(!checkResponse.equals("Success")) {
            return JsonOperation.createJson("Error", checkResponse);
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

        String checkResponse = userService.checkLegitUser(uid,token);
        if(!checkResponse.equals("Success")) {
            return JsonOperation.createJson("Error", checkResponse);
        }

        return userMovieService.removeMovie(uid,movie_id,"watched");
    }


    ///


    @PostMapping("/users/follow_user")
    public Object followUser(@RequestBody Map<String,String> request) throws ExecutionException, InterruptedException {
        String loggedUid;
        String followUid;
        String token;

        loggedUid = request.get("logged_uid");
        followUid = request.get("follow_uid");
        token = request.get("token");

        if(loggedUid == null || followUid == null || token == null) {
            return JsonOperation.createJson("Error", "Invalid request parameters");
        }

        String checkResponse = userService.checkLegitUser(loggedUid,token);
        if(!checkResponse.equals("Success")) {
            return JsonOperation.createJson("Error", checkResponse);
        }

        return UserFollowService.followUser(loggedUid, followUid);
    }

    @GetMapping("/users/get_followed_users")
    public Object getFollowedUsers(@RequestBody Map<String,String> request) throws ExecutionException, InterruptedException, JsonProcessingException {
        String uid;
        String token;

        uid = request.get("uid");
        token = request.get("token");

        if(uid == null || token == null) {
            return JsonOperation.createJson("Error", "Invalid request parameters");
        }

        String checkResponse = userService.checkLegitUser(uid,token);
        if(!checkResponse.equals("Success")) {
            return JsonOperation.createJson("Error", checkResponse);
        }

        return UserFollowService.getFollowers(uid);
    }

    @PostMapping("/users/unfollow_user")
    public Object unfollowUser(@RequestBody Map<String,String> request) throws ExecutionException, InterruptedException {
        String loggedUid;
        String unfollowUid;
        String token;

        loggedUid = request.get("logged_uid");
        unfollowUid = request.get("unfollow_uid");
        token = request.get("token");

        if(loggedUid == null || unfollowUid == null || token == null) {
            return JsonOperation.createJson("Error", "Invalid request parameters");
        }

        String checkResponse = userService.checkLegitUser(loggedUid,token);
        if(!checkResponse.equals("Success")) {
            return JsonOperation.createJson("Error", checkResponse);
        }

        return UserFollowService.unfollowUser(loggedUid, unfollowUid);
    }


    @GetMapping("/users/search_users")
    public Object searchUsers(@RequestBody Map<String,String> request) throws ExecutionException, InterruptedException, JsonProcessingException {
        String uid;
        String query;
        String token;

        uid = request.get("uid");
        query = request.get("query");
        token = request.get("token");

        if(uid == null || query == null || token == null) {
            return JsonOperation.createJson("Error", "Invalid request parameters");
        }

        String checkResponse = userService.checkLegitUser(uid,token);
        if(!checkResponse.equals("Success")) {
            return JsonOperation.createJson("Error", checkResponse);
        }

        return userService.searchUser(query);
    }



}
