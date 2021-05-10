package com.client.webClient.controllers;

import com.client.webClient.beans.MinimalMovie;
import com.client.webClient.beans.User;
import com.client.webClient.exceptions.ServerErrorException;
import com.client.webClient.services.user.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
    private User user;
    @Autowired
    private User otherUser;
    @Autowired
    private MinimalMovie[] minimalMovies;

    @RequestMapping("/login")
    @ResponseBody
    public String login() throws CertificateException, NoSuchAlgorithmException, KeyStoreException, IOException, KeyManagementException
    {
        loginService.postToServer("user2345@company.com","123456");
        System.out.println("Token:"+user.getToken());
        if(user.getToken()==null)
        {
            return "Login error!";
        }
        return "Login success!";
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
    @ResponseBody
    public String addToWatch(@PathVariable("movieid") int movieid) throws CertificateException, NoSuchAlgorithmException, KeyStoreException, IOException, KeyManagementException {
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
        return result;
    }
    @RequestMapping("/addWatched/{movieid}")
    @ResponseBody
    public String addWatched(@PathVariable("movieid") int movieid) throws CertificateException, NoSuchAlgorithmException, KeyStoreException, IOException, KeyManagementException {
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
        return result;
    }
    @RequestMapping("/removeToWatch/{movieid}")
    @ResponseBody
    public String removeToWatch(@PathVariable("movieid") int movieid) throws CertificateException, NoSuchAlgorithmException, KeyStoreException, IOException, KeyManagementException {
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
        return result;
    }
    @RequestMapping("/removeWatched/{movieid}")
    @ResponseBody
    public String removeWatched(@PathVariable("movieid") int movieid) throws CertificateException, NoSuchAlgorithmException, KeyStoreException, IOException, KeyManagementException {
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
        return result;
    }
    @RequestMapping("/listToWatch")
    @ResponseBody
    public MinimalMovie[] listToWatch() throws CertificateException, NoSuchAlgorithmException, KeyStoreException, IOException, KeyManagementException {
        try {
            minimalMovies=watchListsMoviesService.listToWatch();
        } catch (ServerErrorException e) {
            System.out.println(e.getErrorMessage());
        }
        return minimalMovies;
    }
    @RequestMapping("/listWatched")
    @ResponseBody
    public MinimalMovie[] listWatched() throws CertificateException, NoSuchAlgorithmException, KeyStoreException, IOException, KeyManagementException {
        try {
            minimalMovies=watchListsMoviesService.listWatched();
        } catch (ServerErrorException e) {
            System.out.println(e.getErrorMessage());
        }
        return minimalMovies;
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
