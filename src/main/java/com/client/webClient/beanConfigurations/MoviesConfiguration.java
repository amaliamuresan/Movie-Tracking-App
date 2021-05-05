package com.client.webClient.beanConfigurations;

import com.client.webClient.beans.DiscoverMovie;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MoviesConfiguration {
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
}
