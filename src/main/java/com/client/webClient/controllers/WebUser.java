package com.client.webClient.controllers;

import com.client.webClient.beans.MinimalMovie;
import com.client.webClient.beans.User;
import com.client.webClient.data.ClientData;
import com.client.webClient.exceptions.ServerErrorException;
import com.client.webClient.forms.FormLogin;
import com.client.webClient.forms.FormStringHandler;
import com.client.webClient.services.user.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class WebUser {
    @Autowired
    private LoginService loginService;
    @Autowired
    private OtherUserService otherUserService;
    @Autowired
    private RegisterService registerService;
    @Autowired
    private WatchListsService watchListsService;
    @Autowired
    private WatchListsMoviesService watchListsMoviesService;
    @Autowired
    private User otherUser;
    @Autowired
    private MinimalMovie[] minimalMovies;
    @Autowired
    private FormLogin formLogin;
    @Autowired
    private User user;
    @Autowired
    private FormStringHandler formStringHandler;

    @RequestMapping("/login")
    public String login(Model model) throws CertificateException, NoSuchAlgorithmException, KeyStoreException, IOException, KeyManagementException
    {
        //loginService.postToServer("user2345@company.com","123456");
        //System.out.println("Token:"+user.getToken());
        //if(user.getToken()==null)
        //{
        //    return "login";
        //}
        model.addAttribute("formLogin",formLogin);
        model.addAttribute("errorText","");
        model.addAttribute("success","false");
        return "login";
    }
    @RequestMapping("/login/process")
    public String loginProcess(Model model,@ModelAttribute("formLogin") FormLogin form) throws CertificateException, NoSuchAlgorithmException, KeyStoreException, IOException, KeyManagementException
    {
        //loginService.postToServer("user2345@company.com","123456");
        //System.out.println("Token:"+user.getToken());
        //if(user.getToken()==null)
        //{
        //    return "login";
        //}
        formLogin = form;
        loginService.postToServer(form.getEmail(),form.getPassword());
        model.addAttribute("formLogin",formLogin);
        if(user.getToken() == null) {
            System.out.println("Login error");
            model.addAttribute("errorText","Invalid combination / temp ban");
        }
        else
        {
            System.out.println("Login success");
            model.addAttribute("success","true");

        }
        return "login";
    }
    @RequestMapping("/profile/{uid}")
    @ResponseBody
    public User profile(@PathVariable("uid") String uid) throws CertificateException, NoSuchAlgorithmException, KeyStoreException, IOException, KeyManagementException {
        User otherUser2;
        otherUser2=otherUserService.getfromServer(uid);
        BeanUtils.copyProperties(otherUser2,otherUser);
        return otherUser;
    }
    @RequestMapping("/register")
    @ResponseBody
    public String register() throws CertificateException, NoSuchAlgorithmException, KeyStoreException, IOException, KeyManagementException {
        String result;
        Map<String,String> map;
        map=registerService.postToServer("user2345@company.com","123456","Display me");
        if(map.containsKey("Error"))
        {
            result="Registration Error: "+map.get("Error");
        }
        else
        {
            result="Registration success!";
        }
        return result;
    }
    @RequestMapping("/addToWatch/{movieid}")
    public String addToWatch(Model model,@PathVariable("movieid") int movieid) throws CertificateException, NoSuchAlgorithmException, KeyStoreException, IOException, KeyManagementException {
        String result;
        Map<String,String> map;
        map=watchListsService.addToWatch(String.valueOf(movieid));
        if(map.containsKey("Error"))
        {
            result="Add Error: "+map.get("Error");
        }
        else
        {
            result="Add success!";
        }
        model.addAttribute("formStringHandler",formStringHandler);
        return "search_movies";
    }
    @RequestMapping("/addWatched/{movieid}")
    public String addWatched(Model model,@PathVariable("movieid") int movieid) throws CertificateException, NoSuchAlgorithmException, KeyStoreException, IOException, KeyManagementException {
        String result;
        Map<String,String> map;
        map=watchListsService.addWatched(String.valueOf(movieid));
        if(map.containsKey("Error"))
        {
            result="Add Error: "+map.get("Error");
        }
        else
        {
            result="Add success!";
        }
        model.addAttribute("formStringHandler",formStringHandler);
        return "search_movies";
    }
    @RequestMapping("/removeToWatch/{movieid}")
    public String removeToWatch(Model model,@PathVariable("movieid") int movieid) throws CertificateException, NoSuchAlgorithmException, KeyStoreException, IOException, KeyManagementException {
        String result;
        Map<String,String> map;
        map=watchListsService.removeToWatch(String.valueOf(movieid));
        if(map.containsKey("Error"))
        {
            result="Remove Error: "+map.get("Error");
        }
        else
        {
            result="Remove success!";
        }
        return listToWatch(model);
    }
    @RequestMapping("/removeWatched/{movieid}")
    public String removeWatched(Model model,@PathVariable("movieid") int movieid) throws CertificateException, NoSuchAlgorithmException, KeyStoreException, IOException, KeyManagementException {
        String result;
        Map<String,String> map;
        map=watchListsService.removeWatched(String.valueOf(movieid));
        if(map.containsKey("Error"))
        {
            result="Remove Error: "+map.get("Error");
        }
        else
        {
            result="Remove success!";
        }
        return listWatched(model);
    }
    @RequestMapping("/listToWatch")
    public String listToWatch(Model model) throws CertificateException, NoSuchAlgorithmException, KeyStoreException, IOException, KeyManagementException {
        try {
            minimalMovies=watchListsMoviesService.listToWatch();
        } catch (ServerErrorException e) {
            System.out.println(e.getErrorMessage());
        }
        model.addAttribute("discoverMovies",minimalMovies);
        return "to_watch_list";
    }
    @RequestMapping("/listWatched")
    public String listWatched(Model model) throws CertificateException, NoSuchAlgorithmException, KeyStoreException, IOException, KeyManagementException {
        try {
            minimalMovies=watchListsMoviesService.listWatched();
        } catch (ServerErrorException e) {
            System.out.println(e.getErrorMessage());
        }
        model.addAttribute("discoverMovies",minimalMovies);
        return "watched_list";
    }
    @RequestMapping("/listToWatch/{uid}")
    @ResponseBody
    public MinimalMovie[] listToWatch(@PathVariable("uid") String uid) throws CertificateException, NoSuchAlgorithmException, KeyStoreException, IOException, KeyManagementException {
        try {
            minimalMovies=watchListsMoviesService.listToWatch(uid);
        } catch (ServerErrorException e) {
            System.out.println(e.getErrorMessage());
        }

        return minimalMovies;
    }
    @RequestMapping("/listWatched/{uid}")
    @ResponseBody
    public MinimalMovie[] listWatched(@PathVariable("uid") String uid) throws CertificateException, NoSuchAlgorithmException, KeyStoreException, IOException, KeyManagementException {
        try {
            minimalMovies=watchListsMoviesService.listWatched(uid);
        } catch (ServerErrorException e) {
            System.out.println(e.getErrorMessage());
        }
        return minimalMovies;
    }
}
