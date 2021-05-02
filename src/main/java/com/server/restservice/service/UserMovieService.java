package com.server.restservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import com.server.restservice.models.MinimalMovie;
import com.server.restservice.models.SearchMovie;
import com.server.restservice.models.User;
import com.server.restservice.operation.JsonOperation;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ExecutionException;

@Service
public class UserMovieService {
    private static final String COLLECTION_NAME = "users";
    private static final UserService userService = new UserService();

    public Object addToWatchMovie(String uid, String movieId) throws ExecutionException, InterruptedException {
        String jsonBody;
        JsonNode node;
        List<String> movies = new ArrayList<>();


        User user = userService.getUserDetails(uid);
        if(user.getTo_watch_movies() != null) {
            movies = user.getTo_watch_movies();
        }

        movies.add(movieId);

        Firestore dbFirestore = FirestoreClient.getFirestore();

        dbFirestore.collection(COLLECTION_NAME).document(uid).update(Collections.singletonMap("to_watch_movies", movies));

        return JsonOperation.createJson("Success","Movie added successfully to watchlist");

    }

    public Object getToWatchMovies(String uid) throws ExecutionException, InterruptedException, JsonProcessingException {
        List<MinimalMovie> movies = new ArrayList<>();
        List<String> moviesId = userService.getUserDetails(uid).getTo_watch_movies();
        MinimalMovie movie;
        for(String id : moviesId) {
            movie = TMDBService.getMovieDetailsMinimal(id);
            if(movie != null)
                movies.add(movie);
        }

        return movies;

    }

}
