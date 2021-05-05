package com.client.webClient.beanConfigurations;

import com.client.webClient.services.SearchMovieService;
import com.client.webClient.services.DiscoverMoviesService;
import com.client.webClient.services.GreetingService;
import com.client.webClient.ssltemplate.SSLUsingRestTemplateConfigurator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

@Configuration
@PropertySource("classpath:application.properties")
public class ServicesConfiguration {
    @Value("${ourServer.url}")
    private String ourServerUrl;
    @Value("${trust.store}")
    private Resource trustUrl;
    @Value("${trust.store.password}")
    private String trustPassword;
    @Bean
    public SSLUsingRestTemplateConfigurator sslUsingRestTemplateConfigurator() throws CertificateException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException, IOException {
        return new SSLUsingRestTemplateConfigurator(trustUrl,trustPassword);
    }
    @Bean
    public GreetingService greetingService() throws CertificateException, NoSuchAlgorithmException, KeyStoreException, IOException, KeyManagementException {
        return new GreetingService(ourServerUrl);
    }
    @Bean
    public DiscoverMoviesService discoverMoviesService()
    {
        return new DiscoverMoviesService(ourServerUrl);
    }
    @Bean
    public SearchMovieService searchMovieService()
    {
        return new SearchMovieService(ourServerUrl);
    }
    @PostConstruct
    public void afterInit() throws IOException {
        System.out.println("ConfigurationURL:"+ourServerUrl);
        System.out.println("TrustURL:"+trustUrl.getURL());
    }

}
