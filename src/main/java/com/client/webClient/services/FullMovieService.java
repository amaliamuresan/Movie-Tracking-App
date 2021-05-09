package com.client.webClient.services;

import com.client.webClient.beans.DiscoverMovie;
import com.client.webClient.beans.FullMovie;
import com.client.webClient.ssltemplate.SSLUsingRestTemplate;
import com.client.webClient.ssltemplate.SSLUsingRestTemplateConfigurator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

public class FullMovieService {
    private String serverURL;
    @Autowired
    private SSLUsingRestTemplateConfigurator sslUsingRestTemplateConfigurator;
    @Autowired
    private FullMovie fullMovie;

    public FullMovieService(String serverURL) {
        this.serverURL = serverURL;
    }

    public FullMovie getFromServer(int id) throws CertificateException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException, IOException {
        String processedURL=serverURL;
        processedURL+="/api/tmdb/get_movie?id="+id;
        RestTemplate restT=new SSLUsingRestTemplate(sslUsingRestTemplateConfigurator);
        fullMovie=restT.getForObject(processedURL,FullMovie.class);
        return fullMovie;
    }
}
