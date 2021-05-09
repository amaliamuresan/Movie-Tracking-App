package com.client.webClient.beanConfigurations;

import com.client.webClient.services.movies.*;
import com.client.webClient.services.user.LoginService;
import com.client.webClient.services.user.OtherUserService;
import com.client.webClient.services.user.RegisterService;
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
        return new GreetingService();
    }
    @Bean
    public DiscoverMoviesService discoverMoviesService()
    {
        return new DiscoverMoviesService();
    }
    @Bean
    public SearchMovieService searchMovieService()
    {
        return new SearchMovieService();
    }
    @Bean
    public FullMovieService fullMovieService()
    {
        return new FullMovieService();
    }
    @Bean
    public GenresService genresService()
    {
        return new GenresService();
    }
    @Bean
    public LoginService loginService()
    {
        return new LoginService();
    }
    @Bean
    public OtherUserService otherUserService()
    {
        return new OtherUserService();
    }
    @Bean
    public RegisterService registerService()
    {
        return new RegisterService();
    }
    @PostConstruct
    public void afterInit() throws IOException {
        System.out.println("ConfigurationURL:"+ourServerUrl);
        System.out.println("TrustURL:"+trustUrl.getURL());
    }

}
