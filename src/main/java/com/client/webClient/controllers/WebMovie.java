package com.client.webClient.controllers;

import com.client.webClient.beans.DiscoverMovie;
import com.client.webClient.services.SearchMovieService;
import com.client.webClient.services.DiscoverMoviesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

@Controller
@RequestMapping("/movies")
public class WebMovie {
    @Autowired
    private SearchMovieService searchMovieService;
    @Autowired
    private DiscoverMoviesService discoverMoviesService;
    @Autowired
    private DiscoverMovie discoverMovie;
    @Autowired
    private DiscoverMovie[] discoverMovies;
    @Autowired
    private String serverURL;
    @RequestMapping("")
    public String movies(Model model) throws CertificateException, NoSuchAlgorithmException, KeyStoreException, IOException, KeyManagementException {
        discoverMovies= discoverMoviesService.getFromServer();
        model.addAttribute("discoverMovies",discoverMovies);
        return "hello_movies";
    }
    @RequestMapping("/search")
    public String search(Model model) throws CertificateException, NoSuchAlgorithmException, KeyStoreException, IOException, KeyManagementException {
        discoverMovies= searchMovieService.getFromServer("God");
        model.addAttribute("searchMovies",discoverMovies);
        return "hello_movies";
    }


}
