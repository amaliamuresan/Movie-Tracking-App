package com.server.restservice.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.server.restservice.data.ServerData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SearchMovie {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String poster_path;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String id;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String title;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<String> genres = new ArrayList<>();
    //private Map<String,String> genres = new HashMap<String,String>();




    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = ServerData.getTmdbImageUrl() + poster_path;
    }

    public String getId() {
        return id;
    }

    public void setId(int id) {
        this.id = String.valueOf(id);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }



    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    @JsonProperty("genre_ids")
    private void unpackNested(JsonNode nodeArr) {
        //genres = nodeArr;
        if(nodeArr.isArray()) {
            for(JsonNode node : nodeArr) {
                genres.add(ServerData.getTmdbGenres().get(node.toString()));
            }
        }
    }
}
