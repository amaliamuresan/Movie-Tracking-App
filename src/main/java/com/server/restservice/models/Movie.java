package com.server.restservice.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.server.restservice.data.ServerData;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Movie {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String imdb_id;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String id;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String title;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String overview;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String poster_path;
    @JsonInclude
    private List<String> genres = new ArrayList<>();
    @JsonInclude
    private List<CastMember> actors;
    @JsonInclude
    private String director;
    @JsonInclude
    private String imdbRating;

    public String getImdb_id() {
        return imdb_id;
    }

    public void setImdb_id(String imdb_id) {
        this.imdb_id = imdb_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = ServerData.getTmdbImageUrl() + poster_path;
    }

    public List<String> getGenres() {
        return genres;
    }

    public List<CastMember> getActors() {
        return actors;
    }

    public void settActors(List<CastMember> actors) {
        this.actors = actors;
    }

    public String getDirector() {
        return director;
    }

    public void settDirector(String director) {
        this.director = director;
        this.director = this.director.replace("\"","");
    }

    public String getImdbRating() {
        return imdbRating;
    }

    public void settImdbRating(String imdbRating) {
        this.imdbRating = imdbRating;
        this.imdbRating = this.imdbRating.replace("\"","");
    }

    @JsonProperty("genres")
    private void unpackNested(JsonNode nodeArr) {
        //genres = nodeArr;
        if(nodeArr.isArray()) {
            for(JsonNode node : nodeArr) {
                genres.add(ServerData.getTmdbGenres().get(node.get("id").toString()));
            }
        }
    }
}
