package com.client.webClient.controllers;

import com.client.webClient.beans.DiscoverMovie;
import com.client.webClient.beans.FullMovie;
import com.client.webClient.beans.User;
import com.client.webClient.forms.FormLogin;
import com.client.webClient.forms.FormStringHandler;
import com.client.webClient.services.movies.FullMovieService;
import com.client.webClient.services.movies.GenresService;
import com.client.webClient.services.movies.SearchMovieService;
import com.client.webClient.services.movies.DiscoverMoviesService;
import com.client.webClient.services.user.LoginService;
import com.client.webClient.services.user.WatchListsMoviesService;
import com.client.webClient.services.user.WatchListsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.Map;

@Controller
@RequestMapping("/movies")
public class WebMovie {
    @Autowired
    private WatchListsService watchService;
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
    @Autowired
    private User user;

    @RequestMapping("")
    public String movies(Model model) throws CertificateException, NoSuchAlgorithmException, KeyStoreException, IOException, KeyManagementException {
        //discoverMovies= discoverMoviesService.getFromServer();
        discoverMovies = new DiscoverMovie[1];
        model.addAttribute("formStringHandler",formStringHandler);
        model.addAttribute("discoverMovies",discoverMovies);
        model.addAttribute("user", user);
        return "search_movies";
    }

    @RequestMapping("/discover")
    public String moviesDiscover(Model model) throws CertificateException, NoSuchAlgorithmException, KeyStoreException, IOException, KeyManagementException {
        discoverMovies= discoverMoviesService.getFromServer();
        model.addAttribute("discoverMovies",discoverMovies);
        return "discover_movies";
    }

    @RequestMapping("/add_to_watch")
    public String movies(Model model, @RequestParam Map<String,String> allParams) throws CertificateException, NoSuchAlgorithmException, KeyStoreException, IOException, KeyManagementException {
        //discoverMovies= discoverMoviesService.getFromServer();
        String id;

        if(allParams.containsKey("id")) {
            id = allParams.get("id");
        }
        else
        {
            model.addAttribute("formStringHandler",formStringHandler);
            model.addAttribute("discoverMovies",discoverMovies);
            return "search_movies";
        }
        watchService.addToWatch(id);
        model.addAttribute("discoverMovies",discoverMovies);
        model.addAttribute("formStringHandler",formStringHandler);
        model.addAttribute("user", user);
        return "search_movies";
    }

    @PostMapping("/search")
    public String search(Model model,@ModelAttribute("formStringHandler") FormStringHandler form,@RequestBody String reqBody) throws CertificateException, NoSuchAlgorithmException, KeyStoreException, IOException, KeyManagementException {
        System.out.println(reqBody);
        formStringHandler=form;
        discoverMovies= searchMovieService.getFromServer(formStringHandler.getContent());
        model.addAttribute("discoverMovies",discoverMovies);

        return "search_movies";
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
