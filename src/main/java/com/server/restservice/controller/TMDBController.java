package com.server.restservice.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.server.restservice.data.ServerData;
import com.server.restservice.models.SearchMovie;
import com.server.restservice.operation.JsonOperation;
import com.server.restservice.service.TMDBService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

@RestController
@RequestMapping("/api")
public class TMDBController {
        private final String apiKey = ServerData.getTmdbApiKey();
        private final String apiUrl = ServerData.getTmdbApiUrl();
        private final String imageUrl = ServerData.getTmdbImageUrl();

        @GetMapping("/tmdb")
        @ResponseBody
        public JsonNode api_request(@RequestParam Map<String,String> allParams) throws IOException {
            System.out.println(allParams);
            String jsonBody;
            Scanner scanner;
            String omdbUrl = "http://www.omdbapi.com/?apikey=";
            String baseUrl = omdbUrl +apiKey;
            for(Map.Entry<String,String> param : allParams.entrySet()) {
                baseUrl += "&" + param.getKey() + "=" + param.getValue();
            }
            baseUrl.replace(' ', '%');

            System.out.println(baseUrl);

            RestTemplate restTemplate = new RestTemplate();
            jsonBody = restTemplate.getForObject(baseUrl, String.class);

            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonObj = mapper.readTree(jsonBody);
            return jsonObj;

        }
    @GetMapping("/tmdb/get_genres")
    @ResponseBody
    public JsonNode get_genres() {
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(ServerData.getTmdbGenres());
        JsonNode jsonNode = mapper.valueToTree(ServerData.getTmdbGenres());
        return jsonNode;
    }

    @GetMapping("/tmdb/get_movie")
    @ResponseBody
    public Object get_movie(@RequestParam Map<String,String> allParams) throws IOException {
        if(!allParams.containsKey("id"))
        {
            return JsonOperation.createJson("Error", "Invalid arguments for TMDB api");
        }
        return TMDBService.getMovieDetails(allParams.get("id"));
    }

    @GetMapping("/tmdb/discover_movie")
    @ResponseBody
    public Object discover_movie(@RequestParam Map<String,String> allParams) throws IOException {
        String page="1";
        String genre="";
        Map<String, JsonNode> returnedValues = new HashMap<>();
        if(!allParams.containsKey("type"))
        {
            return JsonOperation.createJson("Error", "Invalid arguments for TMDB api");
        }
        if(allParams.containsKey("page")) {
            page = allParams.get("page");
        }
        if(allParams.containsKey("genre")) {
            genre = allParams.get("genre");
        }

        SearchMovie[] movies = TMDBService.discoverMovies(allParams.get("type"),page, genre);

        //returnedValues.put("title",node.get("original_title"));

        return movies;
    }

    @GetMapping("/tmdb/search_movie")
    @ResponseBody
    public Object search_movie(@RequestParam Map<String,String> allParams) throws IOException {
        String page="1";
        if(!allParams.containsKey("title"))
        {
            return JsonOperation.createJson("Error", "You must provide a movie title!");
        }
        if(allParams.containsKey("page")) {
            page = allParams.get("page");
        }

        //returnedValues.put("title",node.get("original_title"));

        return TMDBService.searchMovies(allParams.get("title"),page);
    }
}
