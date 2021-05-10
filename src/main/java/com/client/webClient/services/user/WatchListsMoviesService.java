package com.client.webClient.services.user;

import com.client.webClient.beans.MinimalMovie;
import com.client.webClient.beans.User;
import com.client.webClient.exceptions.ServerErrorException;
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

public class WatchListsMoviesService {
    @Autowired
    private String serverURL;
    @Autowired
    private SSLUsingRestTemplateConfigurator sslUsingRestTemplateConfigurator;
    @Autowired
    private User user;
    @Autowired
    private MinimalMovie[] minimalMovies;

    public MinimalMovie[] listToWatch() throws CertificateException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException, IOException, ServerErrorException {
        String processedURL=serverURL;
        processedURL+="/api/users/get_to_watch_movie";
        RestTemplate restT=new SSLUsingRestTemplate(sslUsingRestTemplateConfigurator);
        Map<String,String> params=new HashMap<>();
        params.put("logged_uid",user.getUid());
        params.put("user_uid",user.getUid());
        params.put("token",user.getToken());
        ObjectMapper om=new ObjectMapper();
        String response;
        response=restT.postForObject(processedURL,params,String.class);
        System.out.println(response);
        if(!om.readTree(response).isArray())
        {
            Map<String,String> map=new HashMap<>();
            map=om.readValue(response,map.getClass());
            throw new ServerErrorException(map);
        }
        minimalMovies=om.readValue(response,MinimalMovie[].class);
        return minimalMovies;
    }

    public MinimalMovie[] listWatched() throws CertificateException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException, IOException, ServerErrorException {
        String processedURL=serverURL;
        processedURL+="/api/users/get_watched_movie";
        RestTemplate restT=new SSLUsingRestTemplate(sslUsingRestTemplateConfigurator);
        Map<String,String> params=new HashMap<>();
        params.put("logged_uid",user.getUid());
        params.put("user_uid",user.getUid());
        params.put("token",user.getToken());
        ObjectMapper om=new ObjectMapper();
        String response;
        response=restT.postForObject(processedURL,params,String.class);
        System.out.println(response);
        if(!om.readTree(response).isArray())
        {
            Map<String,String> map=new HashMap<>();
            map=om.readValue(response,map.getClass());
            throw new ServerErrorException(map);
        }
        minimalMovies=om.readValue(response,MinimalMovie[].class);
        return minimalMovies;
    }

    public MinimalMovie[] listToWatch(String uid) throws CertificateException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException, IOException, ServerErrorException {
        String processedURL=serverURL;
        processedURL+="/api/users/get_to_watch_movie";
        RestTemplate restT=new SSLUsingRestTemplate(sslUsingRestTemplateConfigurator);
        Map<String,String> params=new HashMap<>();
        params.put("logged_uid",user.getUid());
        params.put("user_uid",uid);
        params.put("token",user.getToken());
        ObjectMapper om=new ObjectMapper();
        String response;
        response=restT.postForObject(processedURL,params,String.class);
        System.out.println(response);
        if(!om.readTree(response).isArray())
        {
            Map<String,String> map=new HashMap<>();
            map=om.readValue(response,map.getClass());
            throw new ServerErrorException(map);
        }
        minimalMovies=om.readValue(response,MinimalMovie[].class);
        return minimalMovies;
    }

    public MinimalMovie[] listWatched(String uid) throws CertificateException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException, IOException, ServerErrorException {
        String processedURL=serverURL;
        processedURL+="/api/users/get_watched_movie";
        RestTemplate restT=new SSLUsingRestTemplate(sslUsingRestTemplateConfigurator);
        Map<String,String> params=new HashMap<>();
        params.put("logged_uid",user.getUid());
        params.put("user_uid",uid);
        params.put("token",user.getToken());
        ObjectMapper om=new ObjectMapper();
        String response;
        response=restT.postForObject(processedURL,params,String.class);
        System.out.println(response);
        if(!om.readTree(response).isArray())
        {
            Map<String,String> map=new HashMap<>();
            map=om.readValue(response,map.getClass());
            throw new ServerErrorException(map);
        }
        minimalMovies=om.readValue(response,MinimalMovie[].class);
        return minimalMovies;
    }

}
