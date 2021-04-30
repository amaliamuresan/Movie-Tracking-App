package com.client.webClient.services;

import com.client.webClient.models.Greeting;
import com.client.webClient.ssltemplate.SSLUsingRestTemplate;
import com.client.webClient.ssltemplate.SSLUsingRestTemplateConfigurator;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

public class GreetingService {
    private String serverURL;
    @Autowired
    private SSLUsingRestTemplateConfigurator sslUsingRestTemplateConfigurator;

    public GreetingService(String serverURL) {
        this.serverURL = serverURL;
    }

    public Greeting getFromServer() throws CertificateException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException, IOException {
        Greeting greeting;
        String processedURL=serverURL;
        processedURL+="/greeting";
        RestTemplate restT=new SSLUsingRestTemplate(sslUsingRestTemplateConfigurator);
        greeting=restT.getForObject(processedURL,Greeting.class);
        return greeting;
    }

    public SSLUsingRestTemplateConfigurator getSslUsingRestTemplateConfigurator() {
        return sslUsingRestTemplateConfigurator;
    }

    public void setSslUsingRestTemplateConfigurator(SSLUsingRestTemplateConfigurator sslUsingRestTemplateConfigurator) {
        this.sslUsingRestTemplateConfigurator = sslUsingRestTemplateConfigurator;
    }
}
