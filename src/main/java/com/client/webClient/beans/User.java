package com.client.webClient.beans;

import java.util.List;

public class User {
    private String password;
    private String email;
    private String uid;
    private String token = null;
    private List<String> to_watch_movies;
    private List<String> watched_movies;
    private String display_name;
    private List<String> followed_users;

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

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
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

    public List<String> getFollowed_users() {
        return followed_users;
    }

    public void setFollowed_users(List<String> followed_users) {
        this.followed_users = followed_users;
    }
}
