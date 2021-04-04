package com.server.restservice;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.server.restservice.Greeting;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.Scanner;

@RestController
public class OMDBController {
    private final String apiKey = "35e2fd2";


    public OMDBController() throws MalformedURLException {
    }

    @GetMapping("/movies")
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
}
