package com.server.restservice.models;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

public class User {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String name;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String description;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String password;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String email;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String username;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String uid;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String token = null;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<String> to_watch_movies;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<String> watched_movies;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String display_name;




    public void setToken(String token) {
        this.token = token;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getTo_watch_movies() {
        return to_watch_movies;
    }

    public void setTo_watch_movies(List<String> to_watch_movies) {
        this.to_watch_movies = to_watch_movies;
    }

    public List<String> getWatched_movies() {
        return watched_movies;
    }

    public void setWatched_movies(List<String> watched_movies) {
        this.watched_movies = watched_movies;
    }

    public String getDisplay_name() {
        return display_name;
    }

    public void setDisplay_name(String display_name) {
        this.display_name = display_name;
    }
}
