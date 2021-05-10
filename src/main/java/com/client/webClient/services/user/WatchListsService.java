package com.client.webClient.services.user;

import com.client.webClient.beans.User;
import com.client.webClient.ssltemplate.SSLUsingRestTemplate;
import com.client.webClient.ssltemplate.SSLUsingRestTemplateConfigurator;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.HashMap;
import java.util.Map;

public class WatchListsService {
    @Autowired
    private String serverURL;
    @Autowired
    private SSLUsingRestTemplateConfigurator sslUsingRestTemplateConfigurator;
    @Autowired
    private User user;

    public Map<String, String> addToWatch(String movieID) throws CertificateException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException, IOException {
        String processedURL=serverURL;
        processedURL+="/api/users/add_to_watch_movie";
        RestTemplate restT=new SSLUsingRestTemplate(sslUsingRestTemplateConfigurator);
        Map<String,String> params=new HashMap<>();
        params.put("uid",user.getUid());
        params.put("movie_id",movieID);
        params.put("token",user.getToken());
        System.out.println(params);
        String response;
        ObjectMapper om=new ObjectMapper();
        Map<String,String> map=new HashMap<>();
        response=restT.postForObject(processedURL,params,String.class);
        map=om.readValue(response,map.getClass());
        return map;
    }

    public Map<String, String> addWatched(String movieID) throws CertificateException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException, IOException {
        String processedURL=serverURL;
        processedURL+="/api/users/add_watched_movie";
        RestTemplate restT=new SSLUsingRestTemplate(sslUsingRestTemplateConfigurator);
        Map<String,String> params=new HashMap<>();
        params.put("uid",user.getUid());
        params.put("movie_id",movieID);
        params.put("token",user.getToken());
        String response;
        ObjectMapper om=new ObjectMapper();
        Map<String,String> map=new HashMap<>();
        response=restT.postForObject(processedURL,params,String.class);
        map=om.readValue(response,map.getClass());
        return map;
    }

    public Map<String, String> removeToWatch(String movieID) throws CertificateException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException, IOException {
        String processedURL=serverURL;
        processedURL+="/api/users/remove_to_watch_movie";
        RestTemplate restT=new SSLUsingRestTemplate(sslUsingRestTemplateConfigurator);
        Map<String,String> params=new HashMap<>();
        params.put("uid",user.getUid());
        params.put("movie_id",movieID);
        params.put("token",user.getToken());
        String response;
        ObjectMapper om=new ObjectMapper();
        Map<String,String> map=new HashMap<>();
        response=restT.postForObject(processedURL,params,String.class);
        map=om.readValue(response,map.getClass());
        return map;
    }

    public Map<String, String> removeWatched(String movieID) throws CertificateException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException, IOException {
        String processedURL=serverURL;
        processedURL+="/api/users/remove_watched_movie";
        RestTemplate restT=new SSLUsingRestTemplate(sslUsingRestTemplateConfigurator);
        Map<String,String> params=new HashMap<>();
        params.put("uid",user.getUid());
        params.put("movie_id",movieID);
        params.put("token",user.getToken());
        String response;
        ObjectMapper om=new ObjectMapper();
        Map<String,String> map=new HashMap<>();
        response=restT.postForObject(processedURL,params,String.class);
        map=om.readValue(response,map.getClass());
        return map;
    }

}
