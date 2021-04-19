package com.server.restservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.server.restservice.data.ServerData;
import com.server.restservice.models.CastMember;
import com.server.restservice.models.Movie;
import com.server.restservice.models.SearchMovie;
import com.server.restservice.models.TmdbGenre;
import javafx.util.Pair;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.*;

public class TMDBService {
    public static  Map<String,String> get_genres() throws IOException {
        String apiUrl = ServerData.getTmdbApiUrl();
        String apiKey = ServerData.getTmdbApiKey();
        String jsonBody;
        String genresBody;
        Map<String,String> genresMap = new HashMap<>();
        String url = apiUrl + "genre/movie/list?api_key=" + apiKey;
        System.out.println(url);

        RestTemplate restTemplate = new RestTemplate();
        jsonBody = restTemplate.getForObject(url, String.class);

        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(jsonBody);
        System.out.println("test");
        System.out.println(node.get("genres"));
        TmdbGenre[] genres = mapper.readValue(node.get("genres").toString(), TmdbGenre[].class);
        for(TmdbGenre genre : genres) {
            genresMap.put(genre.getId(),genre.getName());
        }
        return genresMap;
    }

    public static Map<String,String> genresInvertor(Map<String,String> map) {
        Map<String,String> invertedMap = new HashMap<String,String>();
        for(Map.Entry<String,String> entry : map.entrySet()) {
            invertedMap.put(entry.getValue(), entry.getKey());
        }
        return invertedMap;
    }

    public static String getMovieImdbId(String tmdbId) throws JsonProcessingException {
        String apiUrl = ServerData.getTmdbApiUrl();
        String apiKey = ServerData.getTmdbApiKey();
        String jsonBody;
        String url = apiUrl + "movie/" + tmdbId + "/external_ids?api_key=" + apiKey;
        System.out.println(url);

        RestTemplate restTemplate = new RestTemplate();
        jsonBody = restTemplate.getForObject(url, String.class);

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        JsonNode node = mapper.readTree(jsonBody);
        return node.get("imdb_id").toString();
    }

    public static SearchMovie[] discoverMovies(String sort_by, String page, String genre) throws JsonProcessingException {
        String apiUrl = ServerData.getTmdbApiUrl();
        String apiKey = ServerData.getTmdbApiKey();
        String jsonBody;
        String url = apiUrl + "discover/movie?api_key=" + apiKey;
        if(genre != null && !genre.equals(""))
        {
            String genreId = ServerData.getTmdbInvertedGenres().get(genre);
            if(genreId != null)
            {
                System.out.println("Genre id: " + genreId);
                genre = "&with_genres=" + genreId;//+ServerData.getTmdbGenres().get(ServerData.getTmdbInvertedGenres().get(genre));
            }
        }

        url += "&with_original_language=en&include_adult=false&page=" + page + "&sort_by=" + sort_by + ".desc" + genre;
        System.out.println(url);

        RestTemplate restTemplate = new RestTemplate();
        jsonBody = restTemplate.getForObject(url, String.class);

        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(jsonBody);
        jsonBody = node.get("results").toString();
        return mapper.readValue(jsonBody, SearchMovie[].class);
    }

    public static SearchMovie[] searchMovies(String title, String page) throws JsonProcessingException {
        String apiUrl = ServerData.getTmdbApiUrl();
        String apiKey = ServerData.getTmdbApiKey();
        String jsonBody;
        String url = apiUrl + "search/movie?api_key=" + apiKey;


        url += "&include_adult=false&page=" + page + "&query=" + title;
        System.out.println(url);

        RestTemplate restTemplate = new RestTemplate();
        jsonBody = restTemplate.getForObject(url, String.class);

        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(jsonBody);
        jsonBody = node.get("results").toString();


        SearchMovie[] movieList = mapper.readValue(jsonBody, SearchMovie[].class);
        if(movieList == null)
            return null;
        List<SearchMovie> copyList = new ArrayList<>();

        for(SearchMovie movie:movieList){
            if(!movie.getPoster_path().equals(ServerData.getTmdbImageUrl()+"null")) {
                copyList.add(movie);
            }
        }
        SearchMovie[] finalList = new SearchMovie[copyList.size()];
        copyList.toArray(finalList);
        //return mapper.readValue(jsonBody, SearchMovie[].class);
        return finalList;
    }

    public static List<CastMember> getMovieCast(String movieId) throws JsonProcessingException {
        CastMember[] initialCrew;
        List<CastMember> finalCrew = new ArrayList<>();
        String apiUrl = ServerData.getTmdbApiUrl();
        String apiKey = ServerData.getTmdbApiKey();
        String jsonBody;
        String url = apiUrl + "movie/" + movieId  + "/credits" + "?api_key=" + apiKey;
        RestTemplate restTemplate = new RestTemplate();
        jsonBody = restTemplate.getForObject(url, String.class);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(jsonBody);
        jsonBody = node.get("cast").toString();
        initialCrew = mapper.readValue(jsonBody, CastMember[].class);
        for(CastMember crew : initialCrew) {
            if(crew.getKnown_for_department().equals("Acting") && crew.getOrder() <= 5) {
                finalCrew.add(crew);
            }
        }
        return finalCrew;
    }

    public static Map<String,String> getRatingAndDirector(String imdbId) throws JsonProcessingException {
        Map<String,String> map = new HashMap<>();
        String jsonBody;
        String omdbUrl = "http://www.omdbapi.com/?apikey=";
        String baseUrl = omdbUrl + ServerData.getOmdbApiKey() + "&i=" + imdbId;

        System.out.println(baseUrl);

        RestTemplate restTemplate = new RestTemplate();
        jsonBody = restTemplate.getForObject(baseUrl, String.class);

        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonObj = mapper.readTree(jsonBody);
        map.put("director", jsonObj.get("Director").toString());
        map.put("rating", jsonObj.get("imdbRating").toString());
        return map;
    }

    public static Object getMovieDetails(String movieId) throws JsonProcessingException {
        String apiUrl = ServerData.getTmdbApiUrl();
        String apiKey = ServerData.getTmdbApiKey();
        String jsonBody;
        Map<String,String> extraDetails;
        String url = apiUrl + "movie/" + movieId + "?api_key=" + apiKey;
        System.out.println(url);

        RestTemplate restTemplate = new RestTemplate();
        jsonBody = restTemplate.getForObject(url, String.class);
        ObjectMapper mapper = new ObjectMapper();
        Movie movie = mapper.readValue(jsonBody, Movie.class);
        movie.settActors(getMovieCast(movie.getId()));
        extraDetails = getRatingAndDirector(movie.getImdb_id());
        movie.settImdbRating(extraDetails.get("rating"));
        movie.settDirector(extraDetails.get("director"));
        return movie;
    }
}


