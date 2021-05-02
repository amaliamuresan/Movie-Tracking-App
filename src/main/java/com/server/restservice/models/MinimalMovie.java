package com.server.restservice.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.server.restservice.data.ServerData;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MinimalMovie {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String poster_path;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String id;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String title;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<String> genres = new ArrayList<>();




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
