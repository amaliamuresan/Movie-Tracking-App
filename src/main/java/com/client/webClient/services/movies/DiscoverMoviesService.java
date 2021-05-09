package com.client.webClient.services.movies;

import com.client.webClient.beans.DiscoverMovie;
import com.client.webClient.beans.Greeting;
import com.client.webClient.ssltemplate.SSLUsingRestTemplate;
import com.client.webClient.ssltemplate.SSLUsingRestTemplateConfigurator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.Map;

public class DiscoverMoviesService {
    @Autowired
    private String serverURL;
    @Autowired
    private SSLUsingRestTemplateConfigurator sslUsingRestTemplateConfigurator;
    @Autowired
    private DiscoverMovie[] discoverMovies;

    public DiscoverMovie[] getFromServer() throws CertificateException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException, IOException {
        String processedURL=serverURL;
        processedURL+="/api/tmdb/discover_movie?type=popularity";
        RestTemplate restT=new SSLUsingRestTemplate(sslUsingRestTemplateConfigurator);
        discoverMovies=restT.getForObject(processedURL,DiscoverMovie[].class);
        return discoverMovies;
    }
    public DiscoverMovie[] getFromServer(Map<String,String> params) throws CertificateException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException, IOException {
        String processedURL=serverURL;
        processedURL+="/api/tmdb/discover_movie?type=popularity";
        if(params.containsKey("genre"))
        {
            processedURL+="&genre="+params.get("genre");
        }
        if(params.containsKey("page"))
        {
            processedURL+="&page="+params.get("page");
        }
        RestTemplate restT=new SSLUsingRestTemplate(sslUsingRestTemplateConfigurator);
        discoverMovies=restT.getForObject(processedURL,DiscoverMovie[].class);
        return discoverMovies;
    }

}
