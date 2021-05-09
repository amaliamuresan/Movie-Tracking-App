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

public class SearchMovieService {
    @Autowired
    private String serverURL;
    @Autowired
    private SSLUsingRestTemplateConfigurator sslUsingRestTemplateConfigurator;
    @Autowired
    private DiscoverMovie[] discoverMovies;

    public DiscoverMovie[] getFromServer(String title) throws CertificateException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException, IOException {
        String processedURL=serverURL;
        if(title==null || title.equals(""))
        {
            title="g";
        }
        processedURL+="/api/tmdb/search_movie?title="+title;
        RestTemplate restT=new SSLUsingRestTemplate(sslUsingRestTemplateConfigurator);
        discoverMovies=restT.getForObject(processedURL,DiscoverMovie[].class);
        return discoverMovies;
    }

}
