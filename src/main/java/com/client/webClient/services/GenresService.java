package com.client.webClient.services;

import com.client.webClient.beans.FullMovie;
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

public class GenresService {
    private String serverURL;
    @Autowired
    private SSLUsingRestTemplateConfigurator sslUsingRestTemplateConfigurator;
    @Autowired
    private Map<String,String> genres;

    public GenresService(String serverURL) {
        this.serverURL = serverURL;
    }

    public Map<String,String> getFromServer() throws CertificateException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException, IOException {
        String processedURL=serverURL;
        ObjectMapper objMap=new ObjectMapper();
        processedURL+="/api/tmdb/get_genres";
        RestTemplate restT=new SSLUsingRestTemplate(sslUsingRestTemplateConfigurator);
        genres=restT.getForObject(processedURL,genres.getClass());
        return genres;
    }
}
