package com.client.webClient.services.movies;

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

public class GreetingService {
    @Autowired
    private String serverURL;
    @Autowired
    private SSLUsingRestTemplateConfigurator sslUsingRestTemplateConfigurator;
    @Autowired
    private Greeting greeting;

    public Greeting getFromServer() throws CertificateException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException, IOException {
        String processedURL=serverURL;
        processedURL+="/greeting";
        RestTemplate restT=new SSLUsingRestTemplate(sslUsingRestTemplateConfigurator);
        greeting=restT.getForObject(processedURL,Greeting.class);
        return greeting;
    }

}
