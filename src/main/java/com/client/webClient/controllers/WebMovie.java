package com.client.webClient.controllers;

import com.client.webClient.beans.DiscoverMovie;
import com.client.webClient.forms.FormStringHandler;
import com.client.webClient.services.SearchMovieService;
import com.client.webClient.services.DiscoverMoviesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    @Autowired
    private FormStringHandler formStringHandler;
    @RequestMapping("")
    public String movies(Model model) throws CertificateException, NoSuchAlgorithmException, KeyStoreException, IOException, KeyManagementException {
        discoverMovies= discoverMoviesService.getFromServer();
        model.addAttribute("formStringHandler",formStringHandler);
        model.addAttribute("discoverMovies",discoverMovies);
        return "hello_movies";
    }
    @PostMapping("/search")
    public String search(Model model,@ModelAttribute("formStringHandler") FormStringHandler form) throws CertificateException, NoSuchAlgorithmException, KeyStoreException, IOException, KeyManagementException {
        formStringHandler=form;
        discoverMovies= searchMovieService.getFromServer(formStringHandler.getContent());
        model.addAttribute("discoverMovies",discoverMovies);

        return "hello_movies";
    }


}
