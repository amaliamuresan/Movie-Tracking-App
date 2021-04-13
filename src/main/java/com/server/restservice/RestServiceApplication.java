package com.server.restservice;

import com.server.restservice.data.ServerData;
import com.server.restservice.service.TMDBService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.Map;

@SpringBootApplication
public class RestServiceApplication {

	public static void main(String[] args) throws IOException {
		ServerData.setTmdbGenres(TMDBService.get_genres());
		ServerData.setTmdbInvertedGenres(TMDBService.genresInvertor(ServerData.getTmdbGenres()));
		//System.out.println("IMDB ID " + TMDBService.getMovieImdbId("732450"));
		SpringApplication.run(RestServiceApplication.class, args);
	}

}
