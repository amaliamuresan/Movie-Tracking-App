package com.client.webClient.controllers;

import com.client.webClient.beans.DiscoverMovie;
import com.client.webClient.beans.FullMovie;
import com.client.webClient.forms.FormStringHandler;
import com.client.webClient.services.FullMovieService;
import com.client.webClient.services.GenresService;
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
import java.util.Map;

@Controller
@RequestMapping("/movies")
public class WebMovie {
    @Autowired
    private SearchMovieService searchMovieService;
    @Autowired
    private DiscoverMoviesService discoverMoviesService;
    @Autowired
    private FullMovieService fullMovieService;
    @Autowired
    private GenresService genresService;
    @Autowired
    private DiscoverMovie discoverMovie;
    @Autowired
    private DiscoverMovie[] discoverMovies;
    @Autowired
    private String serverURL;
    @Autowired
    private FormStringHandler formStringHandler;
    @Autowired
    private FullMovie fullMovie;
    @Autowired
    private Map<String,String> genres;

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
    @GetMapping("/moviebyid/{id}")
    @ResponseBody
    public FullMovie moviebyid(Model model,@PathVariable("id") int id) throws CertificateException, NoSuchAlgorithmException, KeyStoreException, IOException, KeyManagementException {
        fullMovie=fullMovieService.getFromServer(id);
        return fullMovie;
    }
    @RequestMapping("/genres")
    @ResponseBody
    public Map<String,String> genres(Model model) throws CertificateException, NoSuchAlgorithmException, KeyStoreException, IOException, KeyManagementException {
        genres=genresService.getFromServer();
        return genres;
    }


}
