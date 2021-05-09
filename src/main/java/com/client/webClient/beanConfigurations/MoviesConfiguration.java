package com.client.webClient.beanConfigurations;

import com.client.webClient.beans.DiscoverMovie;
import com.client.webClient.beans.FullMovie;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class MoviesConfiguration {
    @Bean
    public Map<String,String> genres()
    {
        return new HashMap<>();
    }
    @Bean
    public DiscoverMovie discoverMovie()
    {
        return new DiscoverMovie();
    }
    @Bean
    public DiscoverMovie[] discoverMovies()
    {
        return new DiscoverMovie[50];
    }
    @Bean
    public FullMovie fullMovie()
    {
        return new FullMovie();
    }
}
